package woo;

import java.io.Serializable;

public class Notification implements Serializable{
    private String productKey = "";
    private int productPrice;
    private String description = "";

    Notification(String productKey,int productPrice, String description){
        this.productKey = productKey;
        this.productPrice = productPrice;
        this.description = description;
    }
    
    @Override
    public String toString(){
        return this.description + "|" + this.productKey + "|" + this.productPrice;
    }
}
