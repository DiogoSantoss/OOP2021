package woo;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Observer, Serializable {

    private String key = "";
    private String name = "";
    private String address = "";
    private int paidValue = 0;
    private int spentValue = 0;
    private ArrayList<Sale> sales = new ArrayList<Sale>();
    private ClientStatus status = new NormalStatus(this, 0);
    private NotificationType notificationType = new DefaultNotification();

    /**
     * Constructor
     * 
     * @param key
     *          the client's key.
     * @param name
     *          the client's name.
     * @param address
     *          the client's address.
     */
    Client(String key, String name, String address){
        this.key = key;
        this.name = name;
        this.address = address;
    }

    public String getKey(){
        return this.key;
    }

    public String getName(){
        return this.name;
    }
    
    public String getAddress() {
        return this.address;
    }

    public ArrayList<Sale> getSales() { 
        return this.sales; 
    }

    public ClientStatus getStatus() { 
        return this.status; 
    }

    public void setStatus(ClientStatus status){
        this.status = status;
    }

    public int getSpentValue(){
        return this.spentValue;
    }

    public void addSpentValue(double value){ 
        this.spentValue += value; 
    }

    public int getPaidValue(){
        return this.paidValue;
    }

    public void addPaidValue(double value){ 
        this.paidValue += value; 
    }

    public void addSale(Sale sale){
        this.sales.add(sale);
    }

    public ArrayList<Notification> getNotifications(){
        return this.notificationType.getNotifications();
    }

    public void resetNotifications(){
        this.notificationType.clearNotifications();
    }

    public void update(String productKey,int productPrice,String description){
        if(description.equals("BARGAIN") || description.equals("NEW"))
            this.notificationType.update(productKey, productPrice, description);
    }

    public void statusUpgrade(){
        this.status.upgrade();
    }

    public void statusDowngrade(){
        this.status.downgrade();
    }

    @Override
    public String toString(){
        return this.key + "|" + this.name + "|" + this.address + "|" + this.status.toString() + "|"+ this.spentValue + "|" + this.paidValue;
    }
}