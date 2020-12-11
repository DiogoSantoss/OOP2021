package woo.exceptions;

public class NonExistentServiceTypeException extends Exception{
    
    /**Service Level */
    private String serviceType;

    /**
     * @param serviceType
     */
    public NonExistentServiceTypeException(String serviceType){
        this.serviceType = serviceType;
    }

    public String getServiceType(){
        return this.serviceType;
    }
}