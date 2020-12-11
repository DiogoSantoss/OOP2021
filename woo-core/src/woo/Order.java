package woo;

import java.util.ArrayList;

public class Order extends Transaction {

    private Supplier supplier;
    private ArrayList<TransactionProduct> tProducts = new ArrayList<TransactionProduct>();

    Order(int key, Supplier supplier, ArrayList<TransactionProduct> tProducts, int paymentDate){
        super(key);
        this.supplier = supplier;
        this.tProducts = tProducts;
        super.setPaymentDate(paymentDate);
        int baseCost = 0;
        for (TransactionProduct t : tProducts)
            baseCost += t.getProduct().getPrice() * t.getAmount();
        super.setBaseCost(baseCost);
    }

    public Supplier getSupplier(){
        return this.supplier;
    }

    public void update(int date){}
    
    public int pay(int date){ 
        return 0; 
    }

    @Override
    public String toString() {
        String string = super.getKey() + "|" + this.supplier.getKey() + "|" + super.getBaseCost() + "|" + super.getPaymentDate() + "\n";
        for (TransactionProduct t : tProducts)
            string += t.getProduct().getKey() + "|" + t.getAmount() + "\n";
        return string;
    }
}
