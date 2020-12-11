package woo;

import java.util.ArrayList;

public interface NotificationType{

    public ArrayList<Notification> getNotifications();
    public void clearNotifications();
    public void update(String productKey,int productPrice,String description);
}
