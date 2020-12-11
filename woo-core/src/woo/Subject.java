package woo;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class Subject implements Serializable {

    private Map<String,Observer> observers = new TreeMap<String,Observer>();

    public boolean registerObserver(String oKey,Observer o){
        if(observers.containsKey(oKey)){
            return false;
        }
        observers.put(oKey,o);
        return true;
    }

    public void removeObserver(String oKey){
        observers.remove(oKey);
    }

    public void notifyObservers(String key, int price, String description){
        for(Observer o: observers.values())
            o.update(key,price,description);
    }
}
