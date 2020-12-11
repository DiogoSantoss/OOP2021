package woo.exceptions;

public class NonExistentServiceLevelException extends Exception{
    
    /**Service Level */
    private String serviceLevel;

    /**
     * @param serviceLevel
     */
    public NonExistentServiceLevelException(String serviceLevel){
        this.serviceLevel = serviceLevel;
    }

    public String getServiceLevel(){
        return this.serviceLevel;
    }
}
