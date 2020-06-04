package api.interfaces;

import api.entity.Car;
import api.entity.CarOption;

import java.util.List;

public interface CarOptionService extends BaseService<CarOption> {
    void create(long carId, long optionId);
    void delete(long carId, long optionId);
    List<CarOption> getByCar(Car car);
}
