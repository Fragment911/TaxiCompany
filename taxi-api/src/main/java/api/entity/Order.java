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

//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", comment='" + comment + '\'' +
//                ", location='" + location + '\'' +
//                ", target='" + target + '\'' +
//                ", price=" + price +
//                ", account=" + account +
//                ", car=" + car +
//                ", statusOrder='" + statusOrder + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Order order = (Order) o;
//
//        if (Float.compare(order.price, price) != 0) return false;
//        if (comment != null ? !comment.equals(order.comment) : order.comment != null) return false;
//        if (!location.equals(order.location)) return false;
//        if (!target.equals(order.target)) return false;
//        if (!account.equals(order.account)) return false;
//        if (car != null ? !car.equals(order.car) : order.car != null) return false;
//        return statusOrder.equals(order.statusOrder);
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = comment != null ? comment.hashCode() : 0;
//        result = 31 * result + location.hashCode();
//        result = 31 * result + target.hashCode();
//        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
//        result = 31 * result + account.hashCode();
//        result = 31 * result + (car != null ? car.hashCode() : 0);
//        result = 31 * result + statusOrder.hashCode();
//        return result;
//    }
}
