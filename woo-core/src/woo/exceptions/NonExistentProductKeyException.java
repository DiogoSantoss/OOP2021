package woo.exceptions;

public class NonExistentProductKeyException extends Exception {

    /* Product key. */
    private String productKey;

    public NonExistentProductKeyException(String productKey) {
        this.productKey = productKey;
    }

    /**
     * @return the requested key
     */
    public String getKey(){
        return this.productKey;
    }
}
