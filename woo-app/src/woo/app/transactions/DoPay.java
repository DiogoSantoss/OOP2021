package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;             
import woo.app.exceptions.UnknownTransactionKeyException;
import woo.exceptions.NonExistentTransactionKeyException;

/**
 * Pay transaction (sale).
 */
public class DoPay extends Command<Storefront> {

  private Input<Integer> saleKey;
  
  public DoPay(Storefront storefront) {
    super(Label.PAY, storefront);
    saleKey = _form.addIntegerInput(Message.requestTransactionKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try {
      _receiver.pay(saleKey.value());
    }
    catch (NonExistentTransactionKeyException e){
      throw new UnknownTransactionKeyException(e.getKey());
    }
  }

}
