package dao.interfaces;

import api.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDAO extends BaseDAO<Account> {
    List<Account> findByRole(String role);
    Optional<Account> findByLogin(String username);
}
