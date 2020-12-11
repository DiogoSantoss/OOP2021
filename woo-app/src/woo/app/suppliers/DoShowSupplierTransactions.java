package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.exceptions.NonExistentSupplierKeyException;                                                                                                                        


/**
 * Show all transactions for specific supplier.
 */
public class DoShowSupplierTransactions extends Command<Storefront> {

  private Input<String> supplierKey;

  public DoShowSupplierTransactions(Storefront receiver) {
    super(Label.SHOW_SUPPLIER_TRANSACTIONS, receiver);
    supplierKey = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try{
      for(var transaction : _receiver.showSupplierTransaction(supplierKey.value()))
        _display.addLine(transaction.toString());
      _display.display();
    }
    catch(NonExistentSupplierKeyException e){
      throw new UnknownSupplierKeyException(e.getKey());
    }
  }

}
