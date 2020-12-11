package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.exceptions.NonExistentSupplierKeyException;

/**
 * Enable/disable supplier transactions.
 */
public class DoToggleTransactions extends Command<Storefront> {

  private Input<String> supplierKey;

  public DoToggleTransactions(Storefront receiver) {
    super(Label.TOGGLE_TRANSACTIONS, receiver);
    supplierKey = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try{
      if(_receiver.toggleTransactions(supplierKey.value()))
        _display.popup(Message.transactionsOn(supplierKey.value()));
      else 
        _display.popup(Message.transactionsOff(supplierKey.value()));
    }
    catch(NonExistentSupplierKeyException e){
      throw new UnknownSupplierKeyException(e.getKey());
    }
  }

}
