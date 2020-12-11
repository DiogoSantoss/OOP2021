package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                         
import pt.tecnico.po.ui.DialogException;                                                                              
import pt.tecnico.po.ui.Input;                                                                                               
import woo.Storefront;                                                                                                           
import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.NonExistentClientKeyException;

/**
 * Show client.
 */
public class DoShowClient extends Command<Storefront> {

  private Input<String> clientKey;

  public DoShowClient(Storefront storefront) {
    super(Label.SHOW_CLIENT, storefront);
    clientKey = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException, UnknownClientKeyException {
    _form.parse();
    try {
      _display.addLine(_receiver.showClient(clientKey.value()));
      for(var notification : _receiver.showClientNotifications(clientKey.value())){
        _display.addLine(notification.toString());
      }
      _display.display();
    }
    catch (NonExistentClientKeyException e){
      throw new UnknownClientKeyException(e.getKey());
    }
  }

}
