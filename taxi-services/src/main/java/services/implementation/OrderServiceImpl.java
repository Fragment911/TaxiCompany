package services.implementation;

import api.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dao.interfaces.OrderDAO;
import api.interfaces.AccountService;
import api.interfaces.OrderService;

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

    public boolean cancel(Order order) {
        if (order.getPassenger().getId() == accountService.getLoggedAccount().getId() && StatusOrder.AWAIT.name().equals(order.getStatusOrder())) {
            order.setStatusOrder(StatusOrder.CANCELLED.name());
            update(order);
            return true;
        }
        return false;
    }

    public boolean done(long id) {
        Order order = get(id);
        if (accountService.getLoggedAccount().getId() == order.getDriver().getId()) {
            order.setStatusOrder(StatusOrder.DONE.name());
            order.setMark(5);
            tDAO.update(order);
            accountService.calculate(accountService.getLoggedAccount());
            return true;
        }
        return false;
    }

    public List<Order> getByStatusOrder(StatusOrder statusOrder) {
        return tDAO.findByStatusOrder(statusOrder.name());
    }

    public boolean take(Order order) {
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
                tDAO.update(orderForSave);
                return true;
            }
        }
        return false;
    }

    public boolean mark(Order order) {
        Account loggedAccount = accountService.getLoggedAccount();
        Order orderForSave = get(order.getId());
        if (loggedAccount.getId() == orderForSave.getPassenger().getId()) {
            if ((Role.ROLE_PASSENGER.name()).equals(loggedAccount.getRole()) && orderForSave.getStatusOrder().equals(StatusOrder.DONE.name())) {
                if (order.getMark() < 1) {
                    order.setMark(1);
                }
                if (order.getMark() > 5) {
                    order.setMark(5);
                }
                orderForSave.setMark(order.getMark());
                order.setMark(5);
                tDAO.update(orderForSave);
                accountService.calculate(orderForSave.getDriver());
                return true;
            }
        }
        return false;
    }
}
