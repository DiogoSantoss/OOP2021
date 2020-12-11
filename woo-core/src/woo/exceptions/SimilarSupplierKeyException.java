package woo.exceptions;

/** 
 * Exception thrown when a supplier id is invalid. 
*/
public class SimilarSupplierKeyException extends Exception {

    /* Supplier key. */
    private String supplierKey;

    public SimilarSupplierKeyException(String supplierKey) {
        this.supplierKey = supplierKey;
    }

    /** @return name */
    public String getKey() {
        return this.supplierKey;
    }
}
