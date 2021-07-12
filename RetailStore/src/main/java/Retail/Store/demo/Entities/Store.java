package Retail.Store.demo.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private int storeId;

    @Column(name="store_type")
    private String storeType;

    @OneToMany(mappedBy = "store")
    Set<Shopping> shoppings;

    public Store(int storeId, String storeType, Set<Shopping> shoppings) {
        this.storeId = storeId;
        this.storeType = storeType;
        this.shoppings = shoppings;
    }

    public Store() {
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public Set<Shopping> getShoppings() {
        return shoppings;
    }

    public void setShoppings(Set<Shopping> shoppings) {
        this.shoppings = shoppings;
    }
}
