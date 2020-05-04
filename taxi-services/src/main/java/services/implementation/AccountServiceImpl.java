package services.implementation;

import api.entity.Account;
import api.entity.Role;
import api.entity.StatusUser;
import dao.interfaces.AccountDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.AccountService;

import javax.annotation.PostConstruct;
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
}
