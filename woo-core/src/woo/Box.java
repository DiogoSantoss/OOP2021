package woo;

import java.io.Serializable;

public class Box extends Product implements Serializable {

    private String serviceType;

    /**
     * Constructor
     *
     * @param serviceType
     *          the box's type of service
     *
     */
    Box(String key, String serviceType, Supplier supplier, int price, int criticalValue, int stock){
        super(key, supplier, price, criticalValue, stock);
        super.setPeriod(5);
        this.serviceType = serviceType;
    }

    /**
     * @return the product's service type
     */
    public String getServiceType(){
        return serviceType;
    }

    @Override
    public String toString() {
        return "BOX" + "|" + super.toString() + "|" + this.serviceType;
    }
}
