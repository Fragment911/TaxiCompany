package services.interfaces;

import api.entity.Car;
import api.entity.CarOption;
import api.entity.Option;

import java.util.List;

public interface CarOptionService extends BaseService<CarOption> {
    List<CarOption> getByCar(Car car);
    List<CarOption> getByCarAndOption(Car car, Option option);
}
