package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                          
import pt.tecnico.po.ui.DialogException;                                                                                                                                                                                                             
import woo.Storefront;                                                                                                                  

/**
 * Show all clients.
 */
public class DoShowAllClients extends Command<Storefront> {

  public DoShowAllClients(Storefront storefront) {
    super(Label.SHOW_ALL_CLIENTS, storefront);
  }

  @Override
  public void execute() throws DialogException {
    for(var client : _receiver.showAllClients())
      _display.addLine(client.toString());
    _display.display();
  }
}
