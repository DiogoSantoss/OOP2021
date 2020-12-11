package woo;

import java.io.Serializable;
import java.util.ArrayList;

public class DefaultNotification implements NotificationType,Serializable {
    
    private ArrayList<Notification> notifications = new ArrayList<Notification>();

    DefaultNotification(){}

    public ArrayList<Notification> getNotifications(){
        return this.notifications;
    }

    public void clearNotifications(){
        this.notifications.clear();
    }

    public void update(String productKey,int productPrice,String description){
        Notification notification = new Notification(productKey,productPrice,description);
        notifications.add(notification);
    }
}
