package Retail.Store.demo.dataAccess;

import Retail.Store.demo.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    Product getByProductId(int productId);
}
