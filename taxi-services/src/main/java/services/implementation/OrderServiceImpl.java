package services.implementation;

import api.entity.Order;
import api.entity.StatusOrder;
import dao.interfaces.OrderDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, OrderDAO> implements OrderService {
    @Transactional
    public List<Order> getByStatusOrder(StatusOrder statusOrder) {
        return tDAO.findByStatusOrder(statusOrder.name());
    }
}
