package woo.exceptions;

public class NonExistentSupplierKeyException extends Exception {

    /**Supplier key.*/
    private String supplierKey;

    public NonExistentSupplierKeyException(String supplierKey){ 
        this.supplierKey = supplierKey;
    }

    /**
     * @return the requested key
     */
    public String getKey(){
        return this.supplierKey;
    }
}
