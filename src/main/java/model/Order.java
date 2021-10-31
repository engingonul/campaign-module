package model;

public class Order {
    private Product product;
    private long quantity;

    public Order(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order [product=" + product.getCode() + ", quantity=" + quantity + "]";
    }
}
