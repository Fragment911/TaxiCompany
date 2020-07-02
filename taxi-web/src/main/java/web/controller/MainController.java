package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// todo на страничках сделать ошибки вход/регистрации
@Controller
public class MainController { // todo сделать бан пользователя
    @GetMapping("")
    public String index() {
        return "index";
    } // todo поменять jsp на thymeleaf
}