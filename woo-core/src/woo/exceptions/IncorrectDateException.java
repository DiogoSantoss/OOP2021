package woo.exceptions;

/** 
 * Exception for date-related problems. 
*/
public class IncorrectDateException extends Exception {
    
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201709021324L;

    private int days;

    /**
     * @param days
     */
    public IncorrectDateException(int days) {
        this.days = days;
    }

    public int getDays(){
        return this.days;
    }
}
