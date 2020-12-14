package woo.app.suppliers;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;
import woo.app.exceptions.DuplicateSupplierKeyException;
import woo.exceptions.SimilarSupplierKeyException;

/**
 * Register supplier.
 */
public class DoRegisterSupplier extends Command<Storefront> {

  private Input<String> supplierKey;
  private Input<String> name;
  private Input<String> address;

  public DoRegisterSupplier(Storefront receiver) {
    super(Label.REGISTER_SUPPLIER, receiver);
    supplierKey = _form.addStringInput(Message.requestSupplierKey());
    name = _form.addStringInput(Message.requestSupplierName());
    address = _form.addStringInput(Message.requestSupplierAddress());
  }

  @Override
  public void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registerSupplier(supplierKey.value(),name.value(),address.value());
    }
    catch (SimilarSupplierKeyException e){
      throw new DuplicateSupplierKeyException(e.getKey());
    }
  }

}
