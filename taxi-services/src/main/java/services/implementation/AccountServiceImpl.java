package services.implementation;

import api.entity.StatusOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import api.entity.Account;
import api.entity.Role;
import api.entity.StatusUser;
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
    public List<Account> getAll() {
        Account loggedAccount = getLoggedAccount();
        List<Account> accountList = tDAO.getAll().stream().filter(account -> account.getId() != loggedAccount.getId()).collect(Collectors.toList());
        return accountList;
    }

    @Override
    public void update(Account account) {
        if (getLoggedAccount().getId() != account.getId()) {
            Account accountForSave = get(account.getId());
            accountForSave.setRole(account.getRole());
            accountForSave.setStatusUser(account.getStatusUser());
            tDAO.update(accountForSave);
        }
    }

    public List<Account> getFreeDriverList() {
        List<Account> accountList = tDAO.findByRole(Role.ROLE_DRIVER.name()).stream().filter(account -> account.getCar() == null).collect(Collectors.toList());
        return accountList;
    }

    public List<Account> getByRole(String role) {
        return tDAO.findByRole(role);
    }

    public Optional<Account> findByLogin(String username) {
        return tDAO.findByLogin(username);
    }

    public void registrate(HttpServletRequest request, Account account, String encodedPassword) throws ServletException, DataAccessException {
        String password = account.getPassword();
        account.setPassword(encodedPassword);
        account.setRating(5);
        account.setStatusUser(StatusUser.ACTIVE.name());
        account.setRole(Role.ROLE_PASSENGER.name());
        create(account);
        request.login(account.getLogin(), password);
    }

    public Account getLoggedAccount() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean hasOrder() {
        if (Role.ROLE_DRIVER.name().equals(getLoggedAccount().getRole())) {
            return orderService.getAll(StatusOrder.RUN).size() != 0;
        } else {
            return orderService.getAll(StatusOrder.AWAIT).size() != 0;
        }
    }

}
