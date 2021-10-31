package service;

import model.Order;
import model.Product;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class OrderService extends SuperService implements ICrudService<Order> {

    @Override
    public Order get(String name) {
        throw new NotImplementedException();
    }

    @Override
    public Order create(List<String> optionList) {
        if (orderList == null) {
            orderList = new ArrayList<>();
        }

        String productCode = optionList.get(0);
        int quantity = Integer.parseInt(optionList.get(1));
        Product product = getProduct(productCode);
        Order order = null;
        if (product != null) {
            order = new Order(product, quantity);
            orderList.add(order);
            product.setStock(product.getStock() - quantity);
            product.addTotalSum(product.getPrice() * quantity);
            System.out.println("CREATE : " + order);
        } else {
            System.out.println("CREATE : Product " + productCode + " could not be found!");
        }

        return order;
    }
}
