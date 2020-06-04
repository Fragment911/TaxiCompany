package web.security;

import api.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import api.interfaces.AccountService;

import java.util.Optional;

@Service
public class CustomAccountDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Account> accounts = accountService.findByLogin(login);

        accounts
                .orElseThrow(() -> new UsernameNotFoundException("Not found!"));
        return accounts
                .map(CustomAccountDetails::new).get();
    }
}
