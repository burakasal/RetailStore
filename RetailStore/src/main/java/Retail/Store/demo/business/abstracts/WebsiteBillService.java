package Retail.Store.demo.business.abstracts;

import Retail.Store.demo.Entities.Product;

import java.util.List;

public interface WebsiteBillService {
    double getPayByBill(int userId, int cardId, List<Integer> productId, List<Integer> quantity, int storeId);
    String checkCardType(int cardId);
    Product isProductTypePhone(List<Integer> productId);
    int CustomerDuration(int userId, int storeId);
    double getDiscountPercentage(String cardType, int customerDuration);
    double getTotalPrice(List<Integer> productId, List<Integer> quantity);
    double getDiscountByTotalPrice(double totalPrice);
    double calculatePayableAmount(Product productTypePhone, double discountPercentage, double totalPrice, double discountAmountByPrice,
                                  List<Integer> quantity);
}
