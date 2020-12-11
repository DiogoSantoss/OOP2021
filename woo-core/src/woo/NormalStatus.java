package woo;

import java.io.Serializable;

public class NormalStatus extends ClientStatus implements Serializable{

    NormalStatus(Client client, int points){
        super(client, points);
    }

    public double calcCurrentPrice(Sale sale, int date){

        int interval = sale.calculateInterval(date);
        double finalPrice = 0;

        switch(interval){
            case 1:
                finalPrice = sale.getBaseCost() * 0.9;
                break;
            case 2:
                finalPrice = sale.getBaseCost();
                break;
            case 3:
                finalPrice = sale.getBaseCost() + (sale.getBaseCost() * 0.05 * (date - sale.getDeadline()));
                break;
            case 4:
                finalPrice = sale.getBaseCost() + (sale.getBaseCost() * 0.1 * (date - sale.getDeadline()));
                break;
        }
        return finalPrice;
    }

    public void upgrade(){
        if(super.getPoints() > 25000)
            super.getClient().setStatus(new EliteStatus(super.getClient(), super.getPoints()));
        else
            super.getClient().setStatus(new SelectionStatus(super.getClient(), super.getPoints()));
    }

    public void downgrade(){}

    @Override
    public String toString(){
        return "NORMAL";
    }
}