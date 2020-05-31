package api.entity;

import javax.persistence.*;

@Entity
@Table(name = "order1")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String comment;
    private String location;
    private String target;
    private float price;
    private int mark;
    @ManyToOne(targetEntity = Account.class)
    private Account passenger;
    @ManyToOne(targetEntity = Account.class)
    private Account driver;
    @ManyToOne(targetEntity = Car.class)
    private Car car;
    @Column(name = "status_order")
    private String statusOrder;

    public Order() {
    }

    public Order(long id, String comment, String location, String target, float price, int mark, Account passenger, Account driver, Car car, String statusOrder) {
        this.id = id;
        this.comment = comment;
        this.location = location;
        this.target = target;
        this.price = price;
        this.mark = mark;
        this.passenger = passenger;
        this.driver = driver;
        this.car = car;
        this.statusOrder = statusOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Account getPassenger() {
        return passenger;
    }

    public void setPassenger(Account passenger) {
        this.passenger = passenger;
    }

    public Account getDriver() {
        return driver;
    }

    public void setDriver(Account driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }
}
