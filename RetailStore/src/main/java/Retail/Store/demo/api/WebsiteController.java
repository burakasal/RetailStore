package Retail.Store.demo.api;

import Retail.Store.demo.business.abstracts.WebsiteBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class WebsiteController {
    private final WebsiteBillService websiteBillService;

    @Autowired
    public WebsiteController(WebsiteBillService websiteBillService){
        this.websiteBillService = websiteBillService;
    }

    @GetMapping("/getPayByBill")
    public double getPayByBill(@RequestParam int userId, @RequestParam int cardId, @RequestParam List<Integer> productId,
                                         @RequestParam List<Integer> quantity, @RequestParam int storeId){

        return this.websiteBillService.getPayByBill(userId, cardId, productId, quantity, storeId);
    }
}
