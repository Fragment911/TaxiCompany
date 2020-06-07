package services.implementation;

import api.entity.*;
import dao.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import api.interfaces.AccountService;
import api.interfaces.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountRepository> implements AccountService {
    @Autowired
    OrderService orderService;

    @Override
    public List<Account> getAll() { // для админа выводим всех пользователей кроме него самого
        Account loggedAccount = getLoggedAccount();
        List<Account> accountList = tRepository.findAll().stream().filter(account -> account.getId() != loggedAccount.getId()).collect(Collectors.toList());
        return accountList;
    }
// сделать страничку "Профиль"
    @Override
    public void update(Account account) {
        if (getLoggedAccount().getId() != account.getId()) {
            Account accountForSave = get(account.getId());
            accountForSave.setRole(account.getRole());
            accountForSave.setStatusUser(account.getStatusUser());
            tRepository.save(accountForSave);
            if (Role.ROLE_DRIVER.name().equals(account.getRole())) {
                calculate(accountForSave);
            }
        }
    }

    public List<Account> getFreeDriverList() {
        List<Account> accountList = tRepository.findByRole(Role.ROLE_DRIVER.name()).stream().filter(account -> account.getCar() == null).collect(Collectors.toList());
        return accountList;
    }

    public List<Account> getByRole(String role) {
        return tRepository.findByRole(role);
    }

    public Optional<Account> findByLogin(String username) { // метод используется в spring sequrity
        return tRepository.findByLogin(username);
    }

    public void registrate(HttpServletRequest request, Account account, String encodedPassword) throws ServletException {
        String password = account.getPassword();
        account.setPassword(encodedPassword);
        account.setRating(5);
        account.setStatusUser(StatusUser.ACTIVE.name());
        account.setRole(Role.ROLE_PASSENGER.name());
        create(account);
        request.login(account.getLogin(), password); // автовход после регистрации
    }

    public Account getLoggedAccount() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean hasOrder() { // проверка, есть ли ожидающий или запущенный заказ у пассажира, или запущенный заказ у водителя
        if (Role.ROLE_DRIVER.name().equals(getLoggedAccount().getRole())) {
            return orderService.getAll(StatusOrder.RUN).size() != 0;
        } else {
            return (orderService.getAll(StatusOrder.RUN).size() != 0 || orderService.getAll(StatusOrder.AWAIT).size() != 0);
        }
    }

    public boolean calculate(Account account) { // пересчет рейтинга водителя
        if (account == null) {
            account = getLoggedAccount();
        }
        if (account.getCar() == null) {
            account = get(account.getId());
        }
        if (Role.ROLE_DRIVER.name().equals(account.getRole())) {
            List<Order> orderList = orderService.getAll().stream().filter(x -> x.getStatusOrder().equals(StatusOrder.DONE.name())).collect(Collectors.toList());
            float rating = (float) orderList.stream().mapToDouble(Order::getMark).average().getAsDouble();
            account.setRating(rating);
            update(account);
            return true;
        } else {
            account.setRating(0);
            update(account);
            return true;
        }
    }
}
