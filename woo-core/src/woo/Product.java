package woo;

import java.io.Serializable;

public class Product extends Subject implements Serializable{

    private String key = "";
    private Supplier supplier;
    private int price;
    private int criticalValue;
    private int stock = 0;
    private int period;

    /**
     * Constructor
     *
     * @param key
     *          the product's key
     * @param supplier
     *          the product's supplier
     * @param price
     *          the product's price
     * @param criticalValue
     *          the product's minimum value for stock before getting restocked
     * @param stock
     *          the product's stock
     */
    Product(String key, Supplier supplier, int price, int criticalValue, int stock){
        this.key = key;
        this.supplier = supplier;
        this.price = price;
        this.criticalValue = criticalValue;
        this.stock = stock;
    }

    public String getKey() {
        return this.key;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public int getCriticalValue() {
        return this.criticalValue;
    }
    
    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public int getPeriod() { 
        return this.period; 
    }

    public void setPeriod(int period) { 
        this.period = period; 
    }

    @Override
    public String toString() {
        return this.key + "|" + this.supplier.getKey() + "|" + this.price + "|" + this.criticalValue + "|" + this.stock;
    }
}
