package Retail.Store.demo.dataAccess;

import Retail.Store.demo.Entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDao extends JpaRepository<Card, Integer> {
    Card getByCardId(int cardId);
}
