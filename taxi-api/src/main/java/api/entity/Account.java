package api.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String firstname;
    private String lastname;
    private String phone;
    private long rating;
    @Column(name = "status_user")
    private String statusUser;
    private String role;
    @OneToOne(mappedBy = "account")
    private Car car;
//    @OneToMany(targetEntity = Order.class, mappedBy = "account")
//    private List<Order> orderList;

    public Account() {
    }

    public Account(Account account) {
        this.id = account.getId();
        this.login = account.getLogin();
        this.password = account.getPassword();
        this.firstname = account.getFirstname();
        this.lastname = account.getLastname();
        this.phone = account.getPhone();
        this.rating = account.getRating();
        this.statusUser = account.getStatusUser();
        this.role = account.getRole();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Car getCar() {
        return car;
    }

//    public List<Order> getOrderList() {
//        return orderList;
//    }

//    @Override
//    public String toString() {
//        return "Account{" +
//                "id=" + id +
//                ", login='" + login + '\'' +
//                ", password='" + password + '\'' +
//                ", firstname='" + firstname + '\'' +
//                ", lastname='" + lastname + '\'' +
//                ", phone='" + phone + '\'' +
//                ", rating=" + rating +
//                ", statusUser='" + statusUser + '\'' +
//                ", role='" + role + '\'' +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Account account = (Account) o;
//
//        if (rating != account.rating) return false;
//        if (!login.equals(account.login)) return false;
//        if (!password.equals(account.password)) return false;
//        if (!firstname.equals(account.firstname)) return false;
//        if (!lastname.equals(account.lastname)) return false;
//        if (!phone.equals(account.phone)) return false;
//        if (!statusUser.equals(account.statusUser)) return false;
//        return role.equals(account.role);
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = login.hashCode();
//        result = 31 * result + password.hashCode();
//        result = 31 * result + firstname.hashCode();
//        result = 31 * result + lastname.hashCode();
//        result = 31 * result + phone.hashCode();
//        result = 31 * result + (int) (rating ^ (rating >>> 32));
//        result = 31 * result + statusUser.hashCode();
//        result = 31 * result + role.hashCode();
//        return result;
//    }
}
