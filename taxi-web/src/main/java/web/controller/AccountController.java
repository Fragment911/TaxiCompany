package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import api.entity.Account;
import api.entity.Role;
import api.entity.StatusUser;
import api.interfaces.AccountService;
import web.security.WebSecurityConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = {"account"}) //todo как то расписать бан пользователя подумаю еще
public class AccountController {
    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = {""})
    public String getAll(Model model) {
        model.addAttribute("accountList", accountService.getAll());
        return "account/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = {"{id}"})
    public String get(Model model, @PathVariable("id") Long id) {
        model.addAttribute("account", accountService.get(id));
        return "account/info";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = {"update/{id}"})
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("account", accountService.get(id));
        model.addAttribute("roleList", Role.getAll());
        model.addAttribute("statusList", StatusUser.getAll());
        return "account/update";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = {"update"})
    public String update(@Valid Account account) {
        accountService.update(account);
        return "redirect:";
    }

    @GetMapping(value = "login")  //todo Сделать обработчик ошибки авторизации и сообщение пользователю на странице
    public String login() {
        return "account/login";
    }

    @GetMapping(value = "registration")
    public String signUp() {
        return "account/registration";
    }

    @PostMapping(value = "registration")  //todo Сделать обработчик ошибки регистрации и сообщение пользователю на странице
    public String registrate(HttpServletRequest request, @Valid Account account) {
        try {
            String encodedPassword = webSecurityConfig.getShaPasswordEncoder().encodePassword(account.getPassword(), null);
            accountService.registrate(request, account, encodedPassword);
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (DataAccessException ex) {
            String s = "s";
        }
        return "redirect:";
    }
}