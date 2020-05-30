package api.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    @OneToOne
    @JoinColumn(name="account_id")
    private Account account;
    @Column(name = "car_type")
    private String carType;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "car_option", joinColumns = @JoinColumn(name = "car_id"), inverseJoinColumns = @JoinColumn(name = "option_id"))
    private List<Option> optionList;
    @OneToMany(targetEntity = Order.class, mappedBy = "car")
    private List<Order> orderList;

    public Car() {
    }

    public Car(long id, String number, String carType) {
        this.id = id;
        this.number = number;
        this.carType = carType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", account=" + account +
                ", carType='" + carType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!number.equals(car.number)) return false;
        if (account != null ? !account.equals(car.account) : car.account != null) return false;
        return carType.equals(car.carType);

    }

    @Override
    public int hashCode() {
        int result = number.hashCode();
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + carType.hashCode();
        return result;
    }
}
