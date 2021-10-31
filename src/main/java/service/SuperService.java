package service;

import model.Campaign;
import model.Order;
import model.Product;

import java.util.List;

public abstract class SuperService {
    protected static List<Product> productList;
    protected static List<Order> orderList;
    protected static List<Campaign> campaignList;
    protected static int time;

    protected Product getProduct(String code) {
        Product product = null;
        if (productList != null && productList.size() > 0) {
            product = productList.stream()
                    .filter(p -> p.getCode().equals(code))
                    .findFirst()
                    .orElse(null);
        }

        return product;
    }

}
