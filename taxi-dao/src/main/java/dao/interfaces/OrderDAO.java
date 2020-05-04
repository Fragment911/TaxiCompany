package dao.interfaces;

import api.entity.Order;

import java.util.List;

public interface OrderDAO extends BaseDAO<Order> {
    List<Order> findByStatusOrder(String statusOrder);
}
