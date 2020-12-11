package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;                                                                                                         
import woo.app.exceptions.DuplicateProductKeyException;
import woo.app.exceptions.UnknownServiceTypeException;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.exceptions.NonExistentServiceTypeException;
import woo.exceptions.NonExistentSupplierKeyException;
import woo.exceptions.SimilarProductKeyException;

/**
 * Register box.
 */
public class DoRegisterProductBox extends Command<Storefront> {

  private Input<String> productKey;
  private Input<String> serviceType;
  private Input<String> supplierKey;
  private Input<Integer> price;
  private Input<Integer> criticalValue;

  public DoRegisterProductBox(Storefront receiver) {
    super(Label.REGISTER_BOX, receiver);
    productKey = _form.addStringInput(Message.requestProductKey());
    price = _form.addIntegerInput(Message.requestPrice());
    criticalValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    supplierKey = _form.addStringInput(Message.requestSupplierKey());
    serviceType = _form.addStringInput(Message.requestServiceType());
  }

  @Override
  public final void execute() throws DialogException,DuplicateProductKeyException,UnknownSupplierKeyException {
    _form.parse();
    try {
      _receiver.registerProductBox(productKey.value(), serviceType.value(), supplierKey.value(), price.value(), criticalValue.value());
    }
    catch (SimilarProductKeyException e){
      throw new DuplicateProductKeyException(e.getKey());
    }
    catch (NonExistentSupplierKeyException e){
      throw new UnknownSupplierKeyException(e.getKey());
    }
    catch (NonExistentServiceTypeException e){
      throw new UnknownServiceTypeException(e.getServiceType());
    }
  }
}
