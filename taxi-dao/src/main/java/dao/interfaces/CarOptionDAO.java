package dao.interfaces;

import api.entity.Car;
import api.entity.CarOption;
import api.entity.Option;

import java.util.List;

public interface CarOptionDAO extends BaseDAO<CarOption> {
    List<CarOption> findByCar(Car car);
    List<CarOption> findByCarAndOption(Car car, Option option);
    void delete(CarOption carOption);
}
