package woo.app.transactions;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;
import woo.exceptions.NonExistentProductKeyException;
import woo.Storefront;                                                                                                                        //FIXME import other classes
import woo.app.exceptions.UnavailableProductException;
import woo.app.exceptions.UnknownClientKeyException;
import woo.app.exceptions.UnknownProductKeyException;
import woo.exceptions.InsufficientProductException;
import woo.exceptions.NonExistentClientKeyException;

/**
 * Register sale.
 */
public class DoRegisterSaleTransaction extends Command<Storefront> {

  private Input<String> clientKey;
  private Input<Integer> deadline;
  private Input<String> productKey;
  private Input<Integer> amount;

  public DoRegisterSaleTransaction(Storefront receiver) {
    super(Label.REGISTER_SALE_TRANSACTION, receiver);
    clientKey = _form.addStringInput(Message.requestClientKey());
    deadline = _form.addIntegerInput(Message.requestPaymentDeadline());
    productKey = _form.addStringInput(Message.requestProductKey());
    amount = _form.addIntegerInput(Message.requestAmount());
  }

  @Override
  public final void execute() throws DialogException, UnknownClientKeyException, UnknownProductKeyException {
    _form.parse();
    try {
      _receiver.registerSaleTransaction(clientKey.value(), deadline.value(), productKey.value(), amount.value());
    }
    catch (NonExistentClientKeyException e) {
      throw new UnknownClientKeyException(e.getKey());
    }
    catch (NonExistentProductKeyException e){
      throw new UnknownProductKeyException(e.getKey());
    }
    catch (InsufficientProductException e){
      throw new UnavailableProductException(e.getKey(), e.getRequested(), e.getAvailable());
    }
  }

}
