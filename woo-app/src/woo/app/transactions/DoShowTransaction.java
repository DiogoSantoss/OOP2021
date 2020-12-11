package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;                                                                                                                        
import woo.app.exceptions.UnknownTransactionKeyException;
import woo.exceptions.NonExistentTransactionKeyException;

/**
 * Show specific transaction.
 */
public class DoShowTransaction extends Command<Storefront> {

  private Input<Integer> transactionKey;

  public DoShowTransaction(Storefront receiver) {
    super(Label.SHOW_TRANSACTION, receiver);
    transactionKey = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException, UnknownTransactionKeyException{
    _form.parse();
    try{
    _display.addLine(_receiver.showTransaction(transactionKey.value()));
    _display.display();
    }
    catch (NonExistentTransactionKeyException e){
      throw new UnknownTransactionKeyException(e.getKey());
    }
  }

}
