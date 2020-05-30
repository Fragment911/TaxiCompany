package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api.entity.Account;
import api.entity.Role;
import api.entity.StatusUser;
import dao.interfaces.AccountDAO;
import services.interfaces.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account, AccountDAO> implements AccountService {
    @Transactional
    public List<Account> getByRole(String role) {
        return tDAO.findByRole(role);
    }

    @Transactional
    public Optional<Account> findByLogin(String username) {
        return tDAO.findByLogin(username);
    }

    @Transactional
    public void registrate(HttpServletRequest request, Account account, String encodedPassword) throws ServletException, DataAccessException {
        String password = account.getPassword();
        account.setPassword(encodedPassword);
        account.setRating(5);
        account.setStatusUser(StatusUser.ACTIVE.name());
        account.setRole("ROLE_" + Role.PASSENGER.name());
        create(account);
        request.login(account.getLogin(), password);
    }
}
