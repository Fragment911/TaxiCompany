package services.implementation;

import api.entity.Car;
import api.entity.CarOption;
import dao.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.entity.Option;
import api.interfaces.CarOptionService;
import api.interfaces.CarService;
import api.interfaces.OptionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionServiceImpl extends BaseServiceImpl<Option, OptionRepository> implements OptionService {
    @Autowired
    CarService carService;
    @Autowired
    CarOptionService carOptionService;

    public List<Option> getOptionListByCar(Car car) { // получить список опций автомобиля
        List<Option> optionList = new ArrayList<>();
        for (CarOption carOption : carOptionService.getByCar(car)) {
            optionList.add(get(carOption.getOption().getId()));
        }
        return optionList;
    }

    public List<Option> getOtherOptionList(Car car) { // получить список опций, не относящихся к автомобилю
        List<Option> optionList = new ArrayList<>();
        for (Option option: getAll()) {
            if (!car.getOptionList().stream().map(Option::getId).collect(Collectors.toList()).contains(option.getId())) {
                optionList.add(option);
            }
        }
        return optionList;
    }
}
