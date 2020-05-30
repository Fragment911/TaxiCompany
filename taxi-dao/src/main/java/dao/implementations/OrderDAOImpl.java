package dao.implementations;

import org.springframework.stereotype.Repository;
import api.entity.Order;
import dao.interfaces.OrderDAO;
import dao.repository.OrderRepository;

import java.util.List;

@Repository
public class OrderDAOImpl extends BaseDAOImpl<Order, OrderRepository> implements OrderDAO {
    public List<Order> findByStatusOrder(String statusOrder) {
        return tRepository.findByStatusOrder(statusOrder);
    }
}
