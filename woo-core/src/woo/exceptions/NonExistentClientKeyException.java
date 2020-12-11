package woo.exceptions;

/** 
 * Exception for unknown client keys. 
*/
public class NonExistentClientKeyException extends Exception {

  /* Client key. */
  private String clientKey;

  public NonExistentClientKeyException(String clientKey) {
    this.clientKey = clientKey;
  }

  /**
   * @return the requested key
  */
  public String getKey(){
      return this.clientKey;
  }
}
