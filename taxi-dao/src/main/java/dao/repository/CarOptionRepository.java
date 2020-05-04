package dao.repository;

import api.entity.Car;
import api.entity.CarOption;
import api.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarOptionRepository extends JpaRepository<CarOption, Long> {
    List<CarOption> findByCar(Car car);
    List<CarOption> findByCarAndOption(Car car, Option option);
}
