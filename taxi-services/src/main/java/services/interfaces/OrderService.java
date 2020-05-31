package services.interfaces;

import api.entity.Order;
import api.entity.StatusOrder;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    List<Order> getAll(StatusOrder statusOrder);
    List<Order> getByStatusOrder(StatusOrder statusOrder);
    void take(Order order);
    void cancel(Order order);
}
