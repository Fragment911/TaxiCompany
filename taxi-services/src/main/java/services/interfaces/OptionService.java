package services.interfaces;

import api.entity.Car;
import api.entity.Option;

import java.util.List;

public interface OptionService extends BaseService<Option> {
    List<Option> getOptionListByCar(Car car);
    List<Option> getOtherOptionList(Car car);
}
