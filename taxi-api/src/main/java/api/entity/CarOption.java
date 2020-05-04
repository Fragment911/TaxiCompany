package api.entity;
import javax.persistence.*;

@Entity
@Table(name = "car_option")
public class CarOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(targetEntity = Car.class)
    private Car car;
    @ManyToOne(targetEntity = Option.class)
    private Option option;

    public CarOption() {
    }

    public CarOption(Car car, Option option) {
        this.car = car;
        this.option = option;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
