package services.interfaces;

import api.entity.Account;
import api.entity.Role;
import api.entity.StatusUser;

import java.util.List;
import java.util.Optional;

public interface AccountService extends BaseService<Account> {
    List<Account> getByRole(String role);
    Optional<Account> findByLogin(String username);
}
