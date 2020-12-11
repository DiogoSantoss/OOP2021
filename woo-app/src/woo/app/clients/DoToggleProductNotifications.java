package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;
import woo.app.exceptions.UnknownProductKeyException;
import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.NonExistentClientKeyException;
import woo.exceptions.NonExistentProductKeyException;

/**
 * Toggle product-related notifications.
 */
public class DoToggleProductNotifications extends Command<Storefront> {

  private Input<String> clientKey;
  private Input<String> productKey;

  public DoToggleProductNotifications(Storefront storefront) {
    super(Label.TOGGLE_PRODUCT_NOTIFICATIONS, storefront);
    clientKey = _form.addStringInput(Message.requestClientKey());
    productKey = _form.addStringInput(Message.requestProductKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try{
      if(_receiver.toggleProductNotification(clientKey.value(), productKey.value()))
      _display.popup(Message.notificationsOn(clientKey.value(), productKey.value()));
      else
      _display.popup(Message.notificationsOff(clientKey.value(), productKey.value()));
    }
    catch (NonExistentClientKeyException e){
      throw new UnknownClientKeyException(e.getKey());
    }
    catch (NonExistentProductKeyException e){
      throw new UnknownProductKeyException(e.getKey());
    }
  }

}
