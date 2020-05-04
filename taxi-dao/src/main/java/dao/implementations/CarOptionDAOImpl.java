package dao.implementations;

import api.entity.Car;
import api.entity.CarOption;
import api.entity.Option;
import dao.interfaces.CarOptionDAO;
import dao.repository.CarOptionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarOptionDAOImpl extends BaseDAOImpl<CarOption, CarOptionRepository> implements CarOptionDAO {
    public List<CarOption> findByCar(Car car) {
        return tRepository.findByCar(car);
    }
    public List<CarOption> findByCarAndOption(Car car, Option option) {
        return tRepository.findByCarAndOption(car, option);
    }

    public void delete(CarOption carOption) {
        tRepository.delete(carOption);
    }
}
