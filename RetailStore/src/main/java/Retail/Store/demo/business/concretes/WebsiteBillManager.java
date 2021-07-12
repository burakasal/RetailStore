package Retail.Store.demo.business.concretes;

import Retail.Store.demo.Entities.Product;
import Retail.Store.demo.business.abstracts.WebsiteBillService;
import Retail.Store.demo.dataAccess.CardDao;
import Retail.Store.demo.dataAccess.ProductDao;
import Retail.Store.demo.dataAccess.ShoppingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WebsiteBillManager implements WebsiteBillService {
    private static final int DISCOUNT_PER_TWO_HUNDRED = 5;

    @Autowired
    private ShoppingDao shoppingDao;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private ProductDao productDao;

    public WebsiteBillManager(){}
    public WebsiteBillManager(ShoppingDao shoppingDao){
        this.shoppingDao = shoppingDao;
    }
    public WebsiteBillManager(CardDao cardDao){
        this.cardDao = cardDao;
    }
    public WebsiteBillManager(ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    public String checkCardType(int cardId){
        return this.cardDao.getByCardId(cardId).getCardType();
    }

    @Override
    public Product isProductTypePhone(List<Integer> productId){
        var index = 0;
        for (Integer integer : productId) {
            index+=1;
            String productType = this.productDao.getByProductId(integer).getProductType();
            int productPrice = this.productDao.getByProductId(integer).getProductPrice();
            if (productType.equals("Phone")) {
                return new Product(integer, productType, productPrice, index);
            }
        }
        return null;
    }

    @Override
    public int CustomerDuration(int userId, int storeId){
        return this.shoppingDao.getByUser_UserIdAndStore_StoreId(userId, storeId).getCustomerDuration();
    }

    @Override
    public double getDiscountPercentage(String cardType, int customerDuration){
        var discountPercentage = switch (cardType) {
            case "gold" -> 30;
            case "silver" -> 20;
            case "Affiliate" -> 10;
            default -> 0;
        };
        if(discountPercentage==0 && customerDuration>2){
            discountPercentage = 5;
        }
        return discountPercentage;
    }

    @Override
    public double getTotalPrice(List<Integer> productId, List<Integer> quantity) {
        var totalPrice = 0;
        for(var i=0; i<productId.size();i++){
            var productPrice = this.productDao.getByProductId(i).getProductPrice();
            var quantityPerPrice = quantity.get(i);
            totalPrice += productPrice*quantityPerPrice;
        }
        return totalPrice;
    }

    @Override
    public double getDiscountByTotalPrice(double totalPrice) {
        return Math.floor(totalPrice/200)*DISCOUNT_PER_TWO_HUNDRED;
    }

    @Override
    public double calculatePayableAmount(Product productTypePhone, double discountPercentage, double totalPrice, double discountAmountByPrice,
                                         List<Integer> quantity){
        var productPhonePrice = 0;
        if(productTypePhone!=null){
            int productIndex = productTypePhone.getIndex();
            productPhonePrice = productTypePhone.getProductPrice()*quantity.get(productIndex);
            totalPrice = totalPrice-productPhonePrice; //Decrease by phone price in order to calculate discount percentage without it.
            totalPrice = totalPrice- totalPrice*(discountPercentage/100);
            totalPrice = totalPrice+productPhonePrice; //Add the phone price again to the calculated total price.
        }
        else{
            totalPrice = totalPrice- totalPrice*(discountPercentage/100);
        }
        totalPrice = totalPrice - discountAmountByPrice; //Decrease the total price by the general discount
        return totalPrice;
    }

    @Override
    public double getPayByBill(int userId, int cardId, List<Integer> productId, List<Integer> quantity, int storeId) {
        String cardType = checkCardType(cardId);
        var productTypePhone = isProductTypePhone(productId);
        int customerDuration = CustomerDuration(userId, storeId);
        double discountPercentage = getDiscountPercentage(cardType, customerDuration);
        double totalPrice = getTotalPrice(productId, quantity);
        double discountAmountByPrice = getDiscountByTotalPrice(totalPrice);
        return calculatePayableAmount(productTypePhone, discountPercentage, totalPrice, discountAmountByPrice, quantity);
    }


}
