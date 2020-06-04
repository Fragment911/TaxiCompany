package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import api.entity.*;
import api.interfaces.AccountService;
import api.interfaces.CarService;
import web.security.WebSecurityConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
// todo тут еще куча работы
// todo на страничках сделать ошибки вход/регистрации
@Controller
public class MainController { // todo сделать бан пользователя
    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    CarService carService;
    @Autowired
    private AccountService accountService;

    @GetMapping(value = { "", "index" })
    public String index() {
        return "index";
    }

    @GetMapping(value = "403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping(value = "404")
    public String notFound() {
        return "404";
    }
}