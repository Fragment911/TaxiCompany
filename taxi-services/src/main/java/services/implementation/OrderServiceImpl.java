package services.implementation;

import api.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.interfaces.OrderDAO;
import services.interfaces.AccountService;
import services.interfaces.OrderService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderDAO> implements OrderService {
    @Autowired
    AccountService accountService;

    public List<Order> getAll(StatusOrder statusOrder) {
        Account loggedAccount = accountService.getLoggedAccount();
        List<Order> orderList = getByStatusOrder(statusOrder);
        if ((Role.ROLE_PASSENGER.name()).equals(loggedAccount.getRole())) {
            orderList = orderList.stream().filter(order -> order.getPassenger().getId() == loggedAccount.getId()).collect(Collectors.toList());
        }
        if ((Role.ROLE_DRIVER.name()).equals(loggedAccount.getRole()) && !StatusOrder.AWAIT.equals(statusOrder)) {
            orderList = orderList.stream().filter(order -> order.getDriver().getId() == loggedAccount.getId()).collect(Collectors.toList());
        }
        return orderList;
    }

    @Override
    public void create(Order order) {
        if (!accountService.hasOrder()) {
            order.setStatusOrder(StatusOrder.AWAIT.name());
            order.setPrice(10);
            order.setPassenger(accountService.getLoggedAccount());
            tDAO.create(order);
        }
    }

    @Override
    public void update(Order order) {
        if (order.getPassenger().getId() == accountService.getLoggedAccount().getId()) {
            Order orderForSave = get(order.getId());
            orderForSave.setLocation(order.getLocation());
            orderForSave.setTarget(order.getTarget());
            orderForSave.setComment(order.getComment());
            tDAO.update(order);
        }
    }

    public void cancel(Order order) {
        if (order.getPassenger().getId() == accountService.getLoggedAccount().getId() && StatusOrder.AWAIT.name().equals(order.getStatusOrder())) {
            order.setStatusOrder(StatusOrder.CANCELLED.name());
            update(order);
        }
    }

    @Transactional
    public List<Order> getByStatusOrder(StatusOrder statusOrder) {
        return tDAO.findByStatusOrder(statusOrder.name());
    }

    @Transactional
    public void take(Order order) {
        if (!accountService.hasOrder()) {
            Account loggedAccount = accountService.getLoggedAccount();
            loggedAccount = accountService.get(loggedAccount.getId());
            if ((Role.ROLE_DRIVER.name()).equals(loggedAccount.getRole()) && loggedAccount.getCar() != null) {
                Order orderForSave = get(order.getId());
                orderForSave.setStatusOrder(StatusOrder.RUN.name());
                orderForSave.setPrice(order.getPrice());
                Car car = loggedAccount.getCar();
                orderForSave.setCar(car);
                orderForSave.setDriver(loggedAccount);
                update(orderForSave);
            }
        }
    }
}
