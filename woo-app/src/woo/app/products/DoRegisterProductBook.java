package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;                                   
import woo.app.exceptions.DuplicateProductKeyException;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.exceptions.NonExistentSupplierKeyException;
import woo.exceptions.SimilarProductKeyException;

/**
 * Register book.
 */
public class DoRegisterProductBook extends Command<Storefront> {

  private Input<String> productKey;
  private Input<String> title;
  private Input<String> author;
  private Input<String> ISBN;
  private Input<String> supplierKey;
  private Input<Integer> price;
  private Input<Integer> criticalValue;

  public DoRegisterProductBook(Storefront receiver) {
    super(Label.REGISTER_BOOK, receiver);
    productKey = _form.addStringInput(Message.requestProductKey());
    title = _form.addStringInput(Message.requestBookTitle());
    author = _form.addStringInput(Message.requestBookAuthor());
    ISBN = _form.addStringInput(Message.requestISBN());
    price = _form.addIntegerInput(Message.requestPrice());
    criticalValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    supplierKey = _form.addStringInput(Message.requestSupplierKey());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      _receiver.registerProductBook(productKey.value(), title.value(), author.value(), ISBN.value(), supplierKey.value(), price.value(), criticalValue.value());
    }
    catch (SimilarProductKeyException e){
      throw new DuplicateProductKeyException(e.getKey());
    }
    catch (NonExistentSupplierKeyException e){
      throw new UnknownSupplierKeyException(e.getKey());
    }
  }
}
