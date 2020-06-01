package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import api.entity.Account;
import api.entity.Car;
import api.entity.Order;
import api.entity.StatusOrder;
import services.interfaces.AccountService;
import services.interfaces.CarService;
import services.interfaces.OrderService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"/order"})
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = {"/awaitlist"}, method = RequestMethod.GET)
    public String getAwait(Model model) {
        model.addAttribute("orderList", orderService.getAll(StatusOrder.AWAIT));
        model.addAttribute("status", "AWAIT");
        model.addAttribute("title", "Await order");
        model.addAttribute("hasOrder", accountService.hasOrder());
        return "order/list";
    }

    @RequestMapping(value = {"/runlist"}, method = RequestMethod.GET)
    public String getRun(Model model) {
        model.addAttribute("orderList", orderService.getAll(StatusOrder.RUN));
        model.addAttribute("status", "RUN");
        model.addAttribute("title", "Run order");
        model.addAttribute("hasOrder", accountService.hasOrder());
        return "order/list";
    }

    @RequestMapping(value = {"/donelist"}, method = RequestMethod.GET)
    public String getDone(Model model) {
        model.addAttribute("orderList", orderService.getAll(StatusOrder.DONE));
        model.addAttribute("status", "DONE");
        model.addAttribute("title", "Done order list");
        model.addAttribute("hasOrder", accountService.hasOrder());
        return "order/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER', 'PASSENGER')")
    @RequestMapping(value = {"/cancelledlist"}, method = RequestMethod.GET)
    public String getCancelled(Model model) {
        model.addAttribute("orderList", orderService.getAll(StatusOrder.CANCELLED));
        model.addAttribute("status", "CANCELLED");
        model.addAttribute("title", "Cancelled order list");
        model.addAttribute("hasOrder", accountService.hasOrder());
        return "order/list";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
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
    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String empty(Model model) {
        model.addAttribute("statusOrderList", StatusOrder.getAll());
        model.addAttribute("accountList", accountService.getByRole("ROLE_DRIVER"));
        return "/order/create";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String create(@Valid Order order) {
        orderService.create(order);
        return "redirect:/order/awaitlist";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.GET)
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
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public String update(@Valid Order order) {
        orderService.update(order);
        if (order.getPassenger().getId() != accountService.getLoggedAccount().getId()) {
            orderService.update(order);
        }
        return "redirect:/order/awaitlist";
    }

    @PreAuthorize("hasAnyRole('PASSENGER')")
    @RequestMapping(value = {"/cancel/{id}"}, method = RequestMethod.GET)
    public String cancel(@PathVariable("id") Long id) {
        Order order = orderService.get(id);
        if (order.getPassenger().getId() != accountService.getLoggedAccount().getId()) {
            orderService.cancel(order);
        }
        return "redirect:/order/cancelledlist";
    }

    @PreAuthorize("hasAnyRole('DRIVER')")
    @RequestMapping(value = {"/take/{id}"}, method = RequestMethod.GET)
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
    @RequestMapping(value = {"/take"}, method = RequestMethod.POST)
    public String run(@Valid Order order) {
        if (!accountService.hasOrder()) {
            orderService.take(order);
        }
        return "redirect:/order/runlist";
    }

    @PreAuthorize("hasAnyRole('DRIVER')")
    @RequestMapping(value = {"/done/{id}"}, method = RequestMethod.GET)
    public String done(@PathVariable("id") Long id) {
        orderService.done(id);
        return "redirect:/order/donelist";
    }
}
