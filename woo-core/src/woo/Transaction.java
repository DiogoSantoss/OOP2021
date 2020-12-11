package woo;

import java.io.Serializable;

public abstract class Transaction implements Serializable {

    private int key;
    private int baseCost = 0;
    private int paymentDate;

    Transaction(int key){
        this.key = key;
    }

    public int getKey() { 
        return this.key;
    }

    public int getBaseCost(){
        return this.baseCost;
    }

    public void setBaseCost(int baseCost){
        this.baseCost = baseCost;
    }

    public int getPaymentDate(){
        return this.paymentDate;
    }

    public void setPaymentDate(int paymentDate){
        this.paymentDate = paymentDate;
    }

    public abstract void update(int date);
    public abstract int pay(int date);
}
