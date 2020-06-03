package api.entity;

import javax.persistence.*;

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
    private float rating;
    @Column(name = "status_user")
    private String statusUser;
    private String role;
    @OneToOne(mappedBy = "account")
    private Car car;

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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
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
}
