package service;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends SuperService implements ICrudService<Product> {

    @Override
    public Product get(String code) {
        String infoMessage = "Product " + code + " could not be found!";
        Product product = null;
        if (productList != null && productList.size() > 0) {
            product = getProduct(code);
        }
        if (product != null) {
            infoMessage = product.toString();
        }

        System.out.println("INFO : " + infoMessage);

        return product;
    }


    @Override
    public Product create(List<String> optionList) {
        if (productList == null) {
            productList = new ArrayList<>();
        }

        String code = optionList.get(0);
        int price = Integer.parseInt(optionList.get(1));
        int stock = Integer.parseInt(optionList.get(2));
        Product product = getProduct(code);
        if (product == null) {
            product = new Product(code, price, stock);
            productList.add(product);
            System.out.println("CREATE : " + product);
        } else {
            System.out.println("CREATE : There is already a product named " + code);
        }

        return product;
    }
}
