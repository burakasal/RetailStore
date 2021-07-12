package Retail.Store.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name="Shopping")
public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Shopping_id")
    private int shoppingId;

    @Column(name="customer_duration")
    private int customerDuration;

    @ManyToOne
    @MapsId("user_id")
    private User user;

    @ManyToOne
    @MapsId("store_id")
    private Store store;

    public Shopping(int shoppingId, int customerDuration, User user, Store store) {
        this.shoppingId = shoppingId;
        this.customerDuration = customerDuration;
        this.user = user;
        this.store = store;
    }

    public Shopping() {
    }

    public int getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(int shoppingId) {
        this.shoppingId = shoppingId;
    }

    public int getCustomerDuration() {
        return customerDuration;
    }

    public void setCustomerDuration(int customerDuration) {
        this.customerDuration = customerDuration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
