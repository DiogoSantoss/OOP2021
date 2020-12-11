package woo;

public class PriceUnderStrategy implements ProductLookupStrategy{
    
    private int price;

    PriceUnderStrategy(int price){
        this.price = price;
    }

    public boolean ok(Product product){
        return product.getPrice() < price;
    }
}
