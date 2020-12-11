package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;
import woo.app.exceptions.UnknownProductKeyException;
import woo.exceptions.NonExistentProductKeyException;

/**
 * Change product price.
 */
public class DoChangePrice extends Command<Storefront> {

  private Input<String> productKey;
  private Input<Integer> price;
  
  public DoChangePrice(Storefront receiver) {
    super(Label.CHANGE_PRICE, receiver);
    productKey = _form.addStringInput(Message.requestProductKey());
    price = _form.addIntegerInput(Message.requestPrice());
  }

  @Override
  public final void execute() throws DialogException,UnknownProductKeyException {
    _form.parse();
    try{
      _receiver.changeProductPrice(productKey.value(), price.value());
    }
    catch(NonExistentProductKeyException e){
      throw new UnknownProductKeyException(e.getKey());
    }
  }
}
