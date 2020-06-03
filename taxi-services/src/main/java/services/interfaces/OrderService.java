package services.interfaces;

import api.entity.Order;
import api.entity.StatusOrder;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    List<Order> getAll(StatusOrder statusOrder);
    List<Order> getByStatusOrder(StatusOrder statusOrder);
    boolean take(Order order);
    boolean cancel(Order order);
    boolean done(long id);
    boolean mark(Order order);
}
