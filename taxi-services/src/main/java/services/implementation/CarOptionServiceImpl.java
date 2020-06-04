package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.entity.Car;
import api.entity.CarOption;
import dao.interfaces.CarOptionDAO;
import api.interfaces.CarOptionService;
import api.interfaces.CarService;
import api.interfaces.OptionService;

import java.util.List;

@Service
public class CarOptionServiceImpl extends BaseServiceImpl<CarOption, CarOptionDAO> implements CarOptionService {
    @Autowired
    CarService carService;
    @Autowired
    OptionService optionService;

    public void create(long carId, long optionId) {
        CarOption carOption = new CarOption(carService.get(carId), optionService.get(optionId));
        create(carOption);
    }

    public void delete(long carId, long optionId) {
        for (CarOption carOption: tDAO.findByCarAndOption(carService.get(carId), optionService.get(optionId))) {
            delete(carOption.getId());
        }
    }

    public List<CarOption> getByCar(Car car) {
        return tDAO.findByCar(car);
    }
}
