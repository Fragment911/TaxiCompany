package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// todo тут еще куча работы
// todo на страничках сделать ошибки вход/регистрации
@Controller
public class MainController { // todo сделать бан пользователя
    @GetMapping("") //todo комменты на английском
    public String index() {
        return "index";
    } // todo поменять jsp на thymeleaf
} // todo зачем передавать все отдельно, если можно складывать в объекты? (пример: model.addAttribute("orderList", orderService.getAll(StatusOrder.CANCELLED));
// todo model.addAttribute("status", "CANCELLED");
// todo         model.addAttribute("title", "Cancelled order list");
// todo         model.addAttribute("hasOrder", accountService.hasOrder());)