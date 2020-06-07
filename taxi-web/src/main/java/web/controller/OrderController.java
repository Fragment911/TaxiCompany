package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import api.entity.Order;
import api.entity.StatusOrder;
import api.interfaces.AccountService;
import api.interfaces.CarService;
import api.interfaces.OrderService;

import javax.validation.Valid;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CarService carService;

    @GetMapping("awaitlist")
    public String getAwait(Model model) {
        model.addAttribute("orderList", orderService.getAll(StatusOrder.AWAIT));
        model.addAttribute("status", "AWAIT");
        model.addAttribute("title", "Await order");
        model.addAttribute("hasOrder", accountService.hasOrder());
        return "order/list";
    }

    @GetMapping("runlist")
    public String getRun(Model model) {
        model.addAttribute("orderList", orderService.getAll(StatusOrder.RUN));
        model.addAttribute("status", "RUN");
        model.addAttribute("title", "Run order");
        model.addAttribute("hasOrder", accountService.hasOrder());
        return "order/list";
    }

    @GetMapping("donelist")
    public String getDone(Model model) {
        model.addAttribute("orderList", orderService.getAll(StatusOrder.DONE));
        model.addAttribute("status", "DONE");
        model.addAttribute("title", "Done order list");
        model.addAttribute("hasOrder", accountService.hasOrder());
        return "order/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER', 'PASSENGER')")
    @GetMapping("cancelledlist")
    public String getCancelled(Model model) {
        model.addAttribute("orderList", orderService.getAll(StatusOrder.CANCELLED));
        model.addAttribute("status", "CANCELLED");
        model.addAttribute("title", "Cancelled order list");
        model.addAttribute("hasOrder", accountService.hasOrder());
        return "order/list";
    }

    @GetMapping("{id}")
    public String get(Model model, @PathVariable("id") Long id) { //todo запретить просмотр пользователям, которые не имеют отношения к заказу
        Order order = orderService.get(id);
        model.addAttribute("order", order);
        model.addAttribute("hasOrder", accountService.hasOrder());
        switch (order.getStatusOrder()) {
            case "AWAIT":
                model.addAttribute("back", "awaitlist");
                break;
            case "RUN":
                model.addAttribute("back", "runlist");
                break;
            case "DONE":
                model.addAttribute("back", "donelist");
                break;
            case "CANCELLED":
                model.addAttribute("back", "cancelledlist");
                break;
        }
        return "order/info";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @GetMapping("create")
    public String empty(Model model) {
        model.addAttribute("statusOrderList", StatusOrder.getAll());
        model.addAttribute("accountList", accountService.getByRole("ROLE_DRIVER"));
        return "/order/create";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @PostMapping("create")
    public String create(@Valid Order order) {
        orderService.create(order);
        return "redirect:/order/awaitlist";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @GetMapping("update/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Order order = orderService.get(id);
        if (order.getPassenger().getId() != accountService.getLoggedAccount().getId()) {
            return "redirect:/order/awaitlist";
        }
        model.addAttribute("order", order);
        model.addAttribute("statusOrderList", StatusOrder.getAll());
        model.addAttribute("carList", carService.getAll());
        model.addAttribute("accountList", accountService.getByRole("ROLE_DRIVER"));
        switch (order.getStatusOrder()) {
            case "AWAIT":
                model.addAttribute("back", "awaitlist");
                break;
            case "RUN":
                model.addAttribute("back", "runlist");
                break;
            case "DONE":
                model.addAttribute("back", "donelist");
                break;
            case "CANCELLED":
                model.addAttribute("back", "cancelledlist");
                break;
        }
        return "order/update";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @PostMapping("update")
    public String update(@Valid Order order) {
        orderService.update(order);
        if (order.getPassenger().getId() != accountService.getLoggedAccount().getId()) {
            orderService.update(order);
        }
        return "redirect:/order/awaitlist";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @GetMapping("cancel/{id}")
    public String cancel(@PathVariable("id") Long id) {
        Order order = orderService.get(id);
        if (order.getPassenger().getId() != accountService.getLoggedAccount().getId()) {
            orderService.cancel(order);
        }
        return "redirect:/order/cancelledlist";
    }

    @PreAuthorize("hasAnyRole('DRIVER')")
    @GetMapping("take/{id}")
    public String take(Model model, @PathVariable("id") Long id) {
        Order order = orderService.get(id);
        if (!StatusOrder.AWAIT.name().equals(order.getStatusOrder()) || accountService.hasOrder()) {
            return "redirect:/order/awaitlist";
        }
        model.addAttribute("order", order);
        switch (order.getStatusOrder()) {
            case "AWAIT":
                model.addAttribute("back", "awaitlist");
                break;
            case "RUN":
                model.addAttribute("back", "runlist");
                break;
            case "DONE":
                model.addAttribute("back", "donelist");
                break;
            case "CANCELLED":
                model.addAttribute("back", "cancelledlist");
                break;
        }
        return "order/take";
    }

    @PreAuthorize("hasAnyRole('DRIVER')")
    @PostMapping("take")
    public String run(@Valid Order order) {
        if (!accountService.hasOrder()) {
            orderService.take(order);
        }
        return "redirect:/order/runlist";
    }

    @PreAuthorize("hasAnyRole('DRIVER')")
    @GetMapping("done/{id}")
    public String done(@PathVariable("id") Long id) {
        orderService.done(id);
        return "redirect:/order/donelist";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @GetMapping("mark/{id}")
    public String mark(Model model, @PathVariable("id") Long id) {
        Order order = orderService.get(id);
        if (!StatusOrder.DONE.name().equals(order.getStatusOrder()) || order.getPassenger().getId() != accountService.getLoggedAccount().getId()) {
            return "redirect:/order/donelist";
        }
        model.addAttribute("order", order);
        return "order/mark";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @PostMapping("mark")
    public String saveMark(@Valid Order order) {
        orderService.mark(order);
        return "redirect:/order/donelist";
    }
}
