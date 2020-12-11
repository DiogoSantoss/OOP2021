package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                                                                                                                                    
import woo.Storefront;                                                                                                                        

/**
 * Show all suppliers.
 */
public class DoShowSuppliers extends Command<Storefront> {

  public DoShowSuppliers(Storefront receiver) {
    super(Label.SHOW_ALL_SUPPLIERS, receiver);
  }

  @Override
  public void execute() throws DialogException {
    for(var supplier : _receiver.showSuppliers()){
      _display.addLine(supplier.toString());
    }
    _display.display();
  }
}
