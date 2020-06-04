package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.entity.Account;
import api.entity.Car;
import dao.interfaces.CarDAO;
import api.interfaces.AccountService;
import api.interfaces.CarService;

@Service
public class CarServiceImpl extends BaseServiceImpl<Car, CarDAO> implements CarService {
    @Autowired
    AccountService accountService;

    @Override
    public void create(Car car) { // при создании автомобиля проверяем, не приписывается ли ему водитель другого автомобиля
        if (car.getAccount() != null) {
            Account account = accountService.get(car.getAccount().getId());
            if (account.getCar() == null) {
                tDAO.create(car);
            }
        } else {
            tDAO.create(car);
        }
    }

    @Override
    public void update(Car car) { // при обновлении автомобиля проверяем, не приписывается ли ему водитель другого автомобиля
        if (car.getAccount() != null) {
            Account account = accountService.get(car.getAccount().getId());
            if (account.getCar() == null) {
                tDAO.update(car);
            }
        } else {
            tDAO.update(car);
        }
    }
}
