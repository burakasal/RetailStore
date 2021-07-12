package Retail.Store.demo.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @OneToMany(mappedBy = "user")
    Set<Shopping> shoppings;

    public User(int userId, Set<Shopping> shoppings) {
        this.userId = userId;
        this.shoppings = shoppings;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<Shopping> getShoppings() {
        return shoppings;
    }

    public void setShoppings(Set<Shopping> shoppings) {
        this.shoppings = shoppings;
    }
}
