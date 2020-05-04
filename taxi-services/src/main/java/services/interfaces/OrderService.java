package services.interfaces;

import api.entity.Order;
import api.entity.StatusOrder;

import java.util.List;

public interface OrderService extends BaseService<Order> {
    List<Order> getByStatusOrder(StatusOrder statusOrder);
}
