package services.implementation;

import api.entity.Car;
import api.entity.CarOption;
import api.entity.Option;
import dao.interfaces.CarDAO;
import dao.interfaces.CarOptionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.CarService;

@Service
public class CarServiceImpl extends BaseServiceImpl<Car, CarDAO> implements CarService {
}
