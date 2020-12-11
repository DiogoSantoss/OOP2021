package woo;

public class Sale extends Transaction {
    
    private Client client;
    private int deadline;
    private TransactionProduct tProduct;
    private boolean paid = false;
    private int finalCost = 0;

    Sale(int key, Client client, int deadline, Product product, int amount){
        super(key);
        this.client = client;
        this.deadline = deadline;
        this.tProduct = new TransactionProduct(product, amount);
        super.setBaseCost(this.tProduct.getProduct().getPrice() * this.tProduct.getAmount());
    }

    public int getDeadline() {
        return this.deadline;
    }

    public TransactionProduct getTProduct() {
        return this.tProduct;
    }

    public Client getClient(){
        return this.client;
    }   

    public boolean isPaid() { 
        return this.paid; 
    }

    //Used for toString()
    public void update(int date){
        if(!this.isPaid())
            this.setFinalCost(this.currentCost(date));
    }

    public int currentCost(int date){
        return (int)Math.round(this.client.getStatus().calcCurrentPrice(this, date));
    }

    public int getFinalCost(){
        return this.finalCost;
    }

    public void setFinalCost(int finalCost){
        this.finalCost = finalCost;
    }

    public int calculateInterval(int date){

        Product product = this.getTProduct().getProduct();
        int N = product.getPeriod();
        int interval = 0;

        if(this.getDeadline() - date >= N)
            interval = 1;
        else if(this.getDeadline() - date >= 0 && this.getDeadline() - date < N)
            interval = 2;
        else if(date - this.getDeadline() > 0 && date - this.getDeadline() <= N)
            interval = 3;
        else if(date - this.getDeadline() > N)
            interval = 4;

        return interval;
    }

    public int pay(int date) {
        if(this.isPaid())
            return 0;

        //updates sale
        this.paid = true;
        super.setPaymentDate(date);
        this.finalCost = currentCost(date);

        int interval = this.calculateInterval(date);

        //updates client
        this.client.addPaidValue(finalCost);
        this.client.getStatus().updatePoints(interval, finalCost);
        this.client.getStatus().updateStatus(date,this.getDeadline());

        return this.finalCost;
    }

    @Override
    public String toString() {
        String string = super.getKey() + "|" + this.client.getKey() + "|" + this.tProduct.getProduct().getKey() + "|" + 
                      this.tProduct.getAmount() + "|" + super.getBaseCost() + "|" + this.finalCost + "|" + this.deadline;
        if(this.isPaid()) 
            string += "|" + super.getPaymentDate();
        return string;
    }
}


