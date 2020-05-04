package dao.implementations;

import api.entity.Car;
import dao.interfaces.CarDAO;
import dao.repository.CarRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CarDAOImpl extends BaseDAOImpl<Car, CarRepository> implements CarDAO {
}
