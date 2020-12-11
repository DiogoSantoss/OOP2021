package woo.exceptions;

public class InsufficientProductException extends Exception{
    
    private String productKey;
    private int requested;
    private int available;

    public InsufficientProductException(String productKey, int requested, int available){
        this.productKey = productKey;
        this.requested = requested;
        this.available = available;
    }

    public String getKey(){
        return this.productKey;
    }

    public int getRequested(){
        return this.requested;
    }

    public int getAvailable(){
        return this.available;
    }
}
