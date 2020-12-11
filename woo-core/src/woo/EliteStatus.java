package woo;

import java.io.Serializable;

public class EliteStatus extends ClientStatus implements Serializable{
    
    EliteStatus(Client client, int points){
        super(client, points);
    }

    public double calcCurrentPrice(Sale sale, int date){
        int interval = sale.calculateInterval(date);
        double finalPrice = 0;

        switch(interval) {
            case 1:
                finalPrice = (sale.getBaseCost() * 0.9);
                break;
            case 2:
                finalPrice = (sale.getBaseCost() * 0.9);
                break;
            case 3:
                finalPrice = (sale.getBaseCost() * 0.95);
                break;
            case 4:
                finalPrice = sale.getBaseCost();
                break;
        }
        return finalPrice;
    }

    public void upgrade(){}

    public void downgrade(){
        super.decreasePoints(75);
        super.getClient().setStatus(new SelectionStatus(super.getClient(), super.getPoints()));
    }

    @Override
    public String toString(){
        return "ELITE";
    }
}