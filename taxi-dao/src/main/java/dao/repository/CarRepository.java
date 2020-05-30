package dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import api.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
