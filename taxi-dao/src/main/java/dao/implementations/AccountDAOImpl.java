package dao.implementations;

import org.springframework.stereotype.Repository;
import api.entity.Account;
import dao.interfaces.AccountDAO;
import dao.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDAOImpl extends BaseDAOImpl<Account, AccountRepository> implements AccountDAO {
    public List<Account> findByRole(String role) {
        return tRepository.findByRole(role);
    }

    public Optional<Account> findByLogin(String username) {
        return tRepository.findByLogin(username);
    }
}
