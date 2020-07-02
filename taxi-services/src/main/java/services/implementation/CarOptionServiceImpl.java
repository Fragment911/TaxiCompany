package services.implementation;

import dao.repository.CarOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.entity.Car;
import api.entity.CarOption;
import api.interfaces.CarOptionService;
import api.interfaces.CarService;
import api.interfaces.OptionService;

import java.util.List;

@Service // todo тесты, моки
public class CarOptionServiceImpl extends BaseServiceImpl<CarOption, CarOptionRepository> implements CarOptionService { // todo может он не нужен?
    @Autowired
    CarService carService;
    @Autowired
    OptionService optionService;

    public void create(long carId, long optionId) {
        CarOption carOption = new CarOption(carService.get(carId), optionService.get(optionId));
        create(carOption);
    }

    public void delete(long carId, long optionId) {
        for (CarOption carOption: tRepository.findByCarAndOption(carService.get(carId), optionService.get(optionId))) {
            delete(carOption.getId());
        }
    }

    public List<CarOption> getByCar(Car car) {
        return tRepository.findByCar(car);
    }
}
