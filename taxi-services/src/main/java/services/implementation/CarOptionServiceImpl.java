package services.implementation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api.entity.Car;
import api.entity.CarOption;
import api.entity.Option;
import dao.interfaces.CarOptionDAO;
import services.interfaces.CarOptionService;

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
