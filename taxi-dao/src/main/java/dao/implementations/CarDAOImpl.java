package dao.implementations;

import org.springframework.stereotype.Repository;
import api.entity.Car;
import dao.interfaces.CarDAO;
import dao.repository.CarRepository;

@Repository
public class CarDAOImpl extends BaseDAOImpl<Car, CarRepository> implements CarDAO {
}
