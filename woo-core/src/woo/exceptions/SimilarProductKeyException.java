package woo.exceptions;

/** 
 * Exception thrown when a product id is invalid. 
*/
public class SimilarProductKeyException extends Exception {

    /* Product key. */
    private String productKey;

    public SimilarProductKeyException(String productKey){ 
        this.productKey = productKey; 
    }

    /** 
     * @return name 
    */
    public String getKey() {
        return this.productKey;
    }
}
