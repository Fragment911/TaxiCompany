package services.interfaces;

import org.springframework.dao.DataAccessException;
import api.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface AccountService extends BaseService<Account> {
    List<Account> getFreeDriverList();
    List<Account> getByRole(String role);
    Optional<Account> findByLogin(String username);
    void registrate(HttpServletRequest request, Account account, String encodedPassword) throws ServletException, DataAccessException;
    Account getLoggedAccount();
    boolean hasOrder();
}
