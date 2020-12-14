package woo.app.clients;

import pt.tecnico.po.ui.Command;                                                                                                 
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;           
import woo.app.exceptions.UnknownClientKeyException;
import woo.exceptions.NonExistentClientKeyException;

/**
 * Show all transactions for a specific client.
 */
public class DoShowClientTransactions extends Command<Storefront> {

  private Input<String> clientKey;

  public DoShowClientTransactions(Storefront storefront) {
    super(Label.SHOW_CLIENT_TRANSACTIONS, storefront);
    clientKey = _form.addStringInput(Message.requestClientKey());
  }

  @Override
  public void execute() throws DialogException, UnknownClientKeyException {
    _form.parse();
    try {
      for(var transaction : _receiver.showClientTransactions(clientKey.value()))
        _display.addLine(transaction.toString());
      _display.display();
    }
    catch (NonExistentClientKeyException e) {
      throw new UnknownClientKeyException(e.getKey());
    }
  }

}
