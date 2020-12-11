package woo;

import java.io.Serializable;
import java.util.ArrayList;

public class Supplier implements Serializable{

    private String key = "";
    private String name = "";
    private String address = "";
    private boolean state = true;
    private ArrayList<Order> orders = new ArrayList<Order>();


    Supplier(String key, String name, String address){
        this.key = key;
        this.name = name;
        this.address = address;
    }
    /**
     * @return the supplier's id
     */
    public String getKey() {
        return this.key;
    }
    /**
     * @return the supplier's name
     */
    public String getName() {
        return this.name;
    }
    /**
     * @return the supplier's address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @return the supplier's state
     */
    public boolean getState(){
        return this.state;
    }

    public ArrayList<Order> getOrders(){
        return this.orders;
    }

    public void setState(boolean state){
        this.state = state;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    @Override
    public String toString() {

        String message = this.state ? "SIM" : "NÃO";
        return this.key + "|" + this.name + "|" + this.address + "|" + message;
    }
}
