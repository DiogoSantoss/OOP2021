package woo;

public class ClientTransactionPaidStrategy implements TransactionLookupStrategy{
    
    private Client client;

    ClientTransactionPaidStrategy(Client client){
        this.client = client;
    }

    public boolean ok(Transaction transaction){

        if(client.getSales().contains(transaction)){
            Sale sale = client.getSales().get(transaction.getKey());
            return sale.isPaid();
        }
        return false;
    }
}
