package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;                                                                                                                        
import woo.app.exceptions.DuplicateClientKeyException;
import woo.exceptions.SimilarClientKeyException;
/**
 * Register new client.
 */
public class DoRegisterClient extends Command<Storefront> {

  private Input<String> clientKey;
  private Input<String> name;
  private Input<String> address;

  public DoRegisterClient(Storefront storefront) {
    super(Label.REGISTER_CLIENT, storefront);
    clientKey = _form.addStringInput(Message.requestClientKey());
    name = _form.addStringInput(Message.requestClientName());
    address = _form.addStringInput(Message.requestClientAddress());
  }

  @Override
  public void execute() throws DialogException, DuplicateClientKeyException {
    _form.parse();
    try {    
      _receiver.registerClient(clientKey.value(),name.value(),address.value());
    }
    catch (SimilarClientKeyException e){
      throw new DuplicateClientKeyException(e.getKey());
    }
  }

}
