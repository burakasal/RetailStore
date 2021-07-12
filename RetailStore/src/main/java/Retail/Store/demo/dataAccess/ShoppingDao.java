package Retail.Store.demo.dataAccess;

import Retail.Store.demo.Entities.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingDao extends JpaRepository<Shopping, Integer> {
    Shopping getByUser_UserIdAndStore_StoreId(int userId, int storeId);
}
