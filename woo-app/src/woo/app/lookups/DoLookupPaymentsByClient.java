package woo.app.lookups;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;
import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.NonExistentClientKeyException;                                           

/**
 * Lookup payments by given client.
 */
public class DoLookupPaymentsByClient extends Command<Storefront> {

  private Input<String> clientKey;

  public DoLookupPaymentsByClient(Storefront storefront) {
    super(Label.PAID_BY_CLIENT, storefront);
    clientKey = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try{
      for(var transaction:_receiver.LookupPaymentsByClient(clientKey.value()))
        _display.addLine(transaction.toString());
      _display.display();
    }
    catch(NonExistentClientKeyException e){
      throw new UnknownClientKeyException(e.getKey());
    }
  }

}
