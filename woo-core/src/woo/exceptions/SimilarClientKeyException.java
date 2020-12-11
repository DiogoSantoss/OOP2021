package woo.exceptions;

/** 
 * Exception thrown when a client id is invalid. 
*/
public class SimilarClientKeyException extends Exception {
  
  /* Client key. */
  private String clientKey;
  
  public SimilarClientKeyException(String clientKey) {
    this.clientKey = clientKey;
  }
  
  /** 
   * @return name 
  */
  public String getKey() {
    return this.clientKey;
  } 
}
  
