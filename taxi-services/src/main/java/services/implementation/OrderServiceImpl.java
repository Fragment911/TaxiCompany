package services.implementation;

import api.entity.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.interfaces.OrderDAO;
import services.interfaces.OrderService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderDAO> implements OrderService {
    public List<Order> getAll(StatusOrder statusOrder) {
        Account currentAccount = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> orderList = getByStatusOrder(statusOrder);
        if (("ROLE_" + Role.PASSENGER.name()).equals(currentAccount.getRole())) {
            orderList = orderList.stream().filter(order -> order.getPassenger().getId() == currentAccount.getId()).collect(Collectors.toList());
        }
        if (("ROLE_" + Role.DRIVER.name()).equals(currentAccount.getRole()) && !StatusOrder.AWAIT.equals(statusOrder)) {
            orderList = orderList.stream().filter(order -> order.getPassenger().getId() == currentAccount.getId()).collect(Collectors.toList());
        }
        return orderList;
    }
    @Transactional
    public List<Order> getByStatusOrder(StatusOrder statusOrder) {
        return tDAO.findByStatusOrder(statusOrder.name());
    }
}
