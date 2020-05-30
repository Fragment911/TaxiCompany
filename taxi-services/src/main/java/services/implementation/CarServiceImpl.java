package services.implementation;

import org.springframework.stereotype.Service;
import api.entity.Car;
import dao.interfaces.CarDAO;
import services.interfaces.CarService;

@Service
public class CarServiceImpl extends BaseServiceImpl<Car, CarDAO> implements CarService {
}
