package woo;

import java.io.Serializable;

public class Container extends Box implements Serializable{

    private String serviceLevel;

    /**
     * Constructor
     *
     * @param serviceType
     *          the container's type of service
     * @param serviceLevel
     *          the container's level of service
     */
    Container(String id, String serviceType, String serviceLevel, Supplier supplier, int price, int criticalValue, int stock){
        super(id, serviceType, supplier, price, criticalValue, stock);
        super.setPeriod(8);
        this.serviceLevel = serviceLevel;
    }

    public String getServiceLevel(){
        return this.serviceLevel;
    }

    @Override
    public String toString() {
        String string = super.toString() + "|" + this.serviceLevel;
        return string.replace("BOX|","CONTAINER|");
    }
}
