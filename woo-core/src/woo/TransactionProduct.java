package woo;

import java.io.Serializable;

public class TransactionProduct implements Serializable {

    private Product product;
    private int amount = 0;

    TransactionProduct(Product product, int amount){
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getAmount() { 
        return this.amount; 
    }

    public void setProduct(Product product) { 
        this.product = product; 
    }

    public void setAmount(int amount) { 
        this.amount = amount; 
    }
}
