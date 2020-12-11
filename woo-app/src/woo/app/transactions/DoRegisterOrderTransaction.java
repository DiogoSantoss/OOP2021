package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;
import woo.Storefront;
import woo.app.exceptions.UnauthorizedSupplierException;
import woo.app.exceptions.UnknownProductKeyException;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.app.exceptions.WrongSupplierException;
import woo.exceptions.ForbiddenSupplierException;
import woo.exceptions.IncorrectSupplierException;
import woo.exceptions.NonExistentProductKeyException;
import woo.exceptions.NonExistentSupplierKeyException;

import java.util.ArrayList;

/**
 * Register order.
 */
public class DoRegisterOrderTransaction extends Command<Storefront> {

  private Input<String> supplierKey;
  private Input<String> productKey;
  private Input<Integer> amount;
  private Input<String> response;

  public DoRegisterOrderTransaction(Storefront receiver) {
    super(Label.REGISTER_ORDER_TRANSACTION, receiver);
  }

  @Override
  public final void execute() throws DialogException {

    ArrayList<String> productKeys = new ArrayList<String>();
    ArrayList<Integer> amounts = new ArrayList<Integer>();

    supplierKey = _form.addStringInput(Message.requestSupplierKey());
    _form.parse();

    _form.clear();
    productKey = _form.addStringInput(Message.requestProductKey());
    amount = _form.addIntegerInput(Message.requestAmount());
    response = _form.addStringInput(Message.requestMore());

    do{
      _form.parse();
      productKeys.add(productKey.value());
      amounts.add(amount.value());
    }while(response.value().equals("s"));

    _form.clear();

    try{
    _receiver.registerOrderTransaction(supplierKey.value(),productKeys,amounts);
    }
    catch(NonExistentSupplierKeyException e){
      throw new UnknownSupplierKeyException(e.getKey());
    }
    catch(ForbiddenSupplierException e){
      throw new UnauthorizedSupplierException(e.getKey());
    }
    catch(NonExistentProductKeyException e){
      throw new UnknownProductKeyException(e.getKey());
    }
    catch(IncorrectSupplierException e){
      throw new WrongSupplierException(e.getSupplierKey(), e.getProductKey());
    }
  }
}
