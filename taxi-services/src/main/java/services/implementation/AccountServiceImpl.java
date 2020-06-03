package services.implementation;

import api.entity.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import dao.interfaces.AccountDAO;
import services.interfaces.AccountService;
import services.interfaces.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountDAO> implements AccountService {
    @Autowired
    OrderService orderService;

    @Override
    public List<Account> getAll() { // для админа выводим всех пользователей кроме него самого
        Account loggedAccount = getLoggedAccount();
        List<Account> accountList = tDAO.getAll().stream().filter(account -> account.getId() != loggedAccount.getId()).collect(Collectors.toList());
        return accountList;
    }
// сделать страничку "Профиль"
    @Override
    public void update(Account account) {
        if (getLoggedAccount().getId() != account.getId()) {
            Account accountForSave = get(account.getId());
            accountForSave.setRole(account.getRole());
            accountForSave.setStatusUser(account.getStatusUser());
            tDAO.update(accountForSave);
            if (Role.ROLE_DRIVER.name().equals(account.getRole())) {
                calculate(accountForSave);
            }
        }
    }

    public List<Account> getFreeDriverList() {
        List<Account> accountList = tDAO.findByRole(Role.ROLE_DRIVER.name()).stream().filter(account -> account.getCar() == null).collect(Collectors.toList());
        return accountList;
    }

    public List<Account> getByRole(String role) {
        return tDAO.findByRole(role);
    }

    public Optional<Account> findByLogin(String username) { // метод используется в spring sequrity
        return tDAO.findByLogin(username);
    }

    public void registrate(HttpServletRequest request, Account account, String encodedPassword) throws ServletException, DataAccessException {
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
            tDAO.update(account);
            return true;
        } else {
            account.setRating(0);
            tDAO.update(account);
            return true;
        }
    }
}
