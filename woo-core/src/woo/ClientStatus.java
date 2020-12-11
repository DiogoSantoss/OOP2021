package woo;

import java.io.Serializable;

public abstract class ClientStatus implements Serializable {

  private Client client;
  private int points = 0;

  ClientStatus(Client client, int points){
      this.client = client;
      this.points = points;
  }

  public Client getClient(){
    return this.client;
  }

  public int getPoints() { 
      return this.points; 
  }

  public void addPoints(int points){
      this.points += points;
  }

  public void decreasePoints(int percentage){
      this.points -= Math.round(this.points * percentage / 100.0);
  }

  public void updatePoints(int interval, int finalCost){
      if (interval == 1 || interval == 2)
          this.addPoints(finalCost * 10);
  }

  public void updateStatus(int date, int deadline){
      switch (this.toString()){
        case "NORMAL":
          if (points > 2000)
            client.statusUpgrade();
          break;
        case "SELECTION":
          if (date - deadline > 2)
            client.statusDowngrade();
          else if (points > 25000)
            client.statusUpgrade();
          break;
        case "ELITE":
          if (date - deadline > 15)
            client.statusDowngrade();
          break;
      }
  }

  public abstract void upgrade();
  public abstract void downgrade();
  public abstract double calcCurrentPrice(Sale sale, int date);
}