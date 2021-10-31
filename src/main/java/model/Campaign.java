package model;

public class Campaign {
    private Product product;
    private String name;
    private int duration;
    private int pmLimit;
    private int targetSaleCount;
    private int totalSaleCount;
    private int avgPrice;
    private boolean isActive;
    private int remainingHours;
    private int initialPrice;
    private int initialStock;

    public Campaign(String name, Product product, int duration, int pmLimit, int targetSaleCount) {
        this.name = name;
        this.product = product;
        this.duration = duration;
        this.pmLimit = pmLimit;
        this.targetSaleCount = targetSaleCount;
        this.totalSaleCount = 0;
        this.avgPrice = product.getPrice();
        this.isActive = true;
        this.remainingHours = duration;
        this.initialStock = product.getStock();
        this.initialPrice = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getPmLimit() {
        return pmLimit;
    }

    public int getTargetSaleCount() {
        return targetSaleCount;
    }

    public int getTotalSaleCount() {
        return this.initialStock - this.getProduct().getStock();
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getRemainingHours() {
        return remainingHours;
    }

    public void setRemainingHours(int remainingHours) {
        this.remainingHours = remainingHours;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public int getInitialStock() {
        return initialStock;
    }

    @Override
    public String toString() {
        return "Campaign [name=" + name
                + ", product=" + this.product.getCode()
                + ", status=" + (this.isActive() == true ? "Active" : "Ended")
                + ", target_sales=" + targetSaleCount
                + ", total_sales=" + getTotalSaleCount()
                + ", turnover=" + avgPrice * getTotalSaleCount()
                + ", average_price=" + avgPrice
                +  "]";
    }
}
