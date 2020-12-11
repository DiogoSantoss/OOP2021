package woo.exceptions;

public class ForbiddenSupplierException extends Exception{

    private String supplierKey;

    public ForbiddenSupplierException(String supplierKey){
        this.supplierKey = supplierKey;
    }

    public String getKey(){
        return this.supplierKey;
    } 
}
