package woo.exceptions;

public class NonExistentTransactionKeyException extends Exception {

    /* Transaction key. */
    private int transactionKey;

    public NonExistentTransactionKeyException(int transactionKey) {
        this.transactionKey = transactionKey;
    }

    /**
     * @return the requested key
     */
    public int getKey(){
        return this.transactionKey;
    }

}
