package woo.exceptions;

public class IncorrectSupplierException extends Exception{
    
    private String supplierKey;
    private String productKey;

    public IncorrectSupplierException(String supplierKey,String productKey){
        this.supplierKey = supplierKey;
        this.productKey = productKey;
    }

    public String getSupplierKey(){
        return this.supplierKey;
    }

    public String getProductKey(){
        return this.productKey;
    }
}
