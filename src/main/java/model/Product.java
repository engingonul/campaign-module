package model;


public class Product {
    private String code;
    private int price;
    private int stock;
    private int totalSum;

    public Product(String code, int price, int stock) {
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.totalSum = 0;
    }

    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getTotalSum() {
        return totalSum;
    }

    public void addTotalSum(int totalSum) {
        this.totalSum += totalSum;
    }

    @Override
    public String toString() {
        return "Product [code=" + code + ", price=" + price + ", stock=" + stock + "]";
    }
}
