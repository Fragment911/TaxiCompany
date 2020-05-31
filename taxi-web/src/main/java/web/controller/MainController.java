package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import api.entity.*;
import services.interfaces.AccountService;
import services.interfaces.CarService;
import web.security.WebSecurityConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController { // todo сделать бан пользователя
    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    CarService carService;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)  //todo Сделать обработчик ошибки авторизации и сообщение пользователю на странице
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String signUp() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)  //todo Сделать обработчик ошибки регистрации и сообщение пользователю на странице
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

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFound() {
        return "404";
    }
}