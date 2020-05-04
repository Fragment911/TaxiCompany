package services.implementation;

import api.entity.Car;
import api.entity.CarOption;
import api.entity.Option;
import dao.interfaces.CarDAO;
import dao.interfaces.CarOptionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.interfaces.CarOptionService;
import services.interfaces.CarService;

import java.util.List;

@Service
public class CarOptionServiceImpl extends BaseServiceImpl<CarOption, CarOptionDAO> implements CarOptionService {

    @Transactional
    public List<CarOption> getByCar(Car car) {
        return tDAO.findByCar(car);
    }

    @Transactional
    public List<CarOption> getByCarAndOption(Car car, Option option) {
        return tDAO.findByCarAndOption(car, option);
    }
}
