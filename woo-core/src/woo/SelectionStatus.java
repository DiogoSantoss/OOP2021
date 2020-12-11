package woo;

import java.io.Serializable;

public class SelectionStatus extends ClientStatus implements Serializable{
  
    SelectionStatus(Client client, int points){
        super(client, points);
    }

    public double calcCurrentPrice(Sale sale, int date){
        int interval = sale.calculateInterval(date);
        double finalPrice = 0;

        switch(interval) {
            case 1:
                finalPrice = sale.getBaseCost() * 0.9;
                break;
            case 2:
                if (sale.getDeadline() - date >= 2)
                    finalPrice = sale.getBaseCost() * 0.95;
                else
                    finalPrice = sale.getBaseCost();
                break;
            case 3:
                if (date - sale.getDeadline() == 1)
                    finalPrice = sale.getBaseCost();
                else
                    finalPrice = sale.getBaseCost() + (sale.getBaseCost() * 0.02 * (date - sale.getDeadline()));
                break;
            case 4:
                finalPrice = sale.getBaseCost() + (sale.getBaseCost() * 0.05 * (date - sale.getDeadline()));
                break;
        }
        return finalPrice;
    }

    public void upgrade(){
        super.getClient().setStatus(new EliteStatus(super.getClient(), super.getPoints()));
    }

    public void downgrade(){
        super.decreasePoints(90);
        super.getClient().setStatus(new NormalStatus(super.getClient(), super.getPoints()));
    }

    @Override
    public String toString(){
        return "SELECTION";
    }
}