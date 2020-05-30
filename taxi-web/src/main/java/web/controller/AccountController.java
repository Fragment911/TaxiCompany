package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import api.entity.Account;
import api.entity.Role;
import api.entity.StatusUser;
import services.interfaces.AccountService;
import web.security.WebSecurityConfig;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/account"}) //todo как то расписать бан пользователя подумаю еще
public class AccountController {
    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("accountList", accountService.getAll());
        return "account/list";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String get(Model model, @PathVariable("id") Long id) {
        model.addAttribute("account", accountService.get(id));
        return "account/info";
    }

    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("account", accountService.get(id));
        model.addAttribute("roleList", Role.getAll());
        model.addAttribute("statusList", StatusUser.getAll());
        return "account/update";
    }

    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public String update(@Valid Account account) {
        accountService.update(account);
        return "redirect:";
    }

//    @RequestMapping(value = {"/remove/{id}"}, method = RequestMethod.GET)
//    public RedirectView delete(@PathVariable("id") Long id) {
//        accountService.delete(id);
//        return new RedirectView("/account");
//    }
}