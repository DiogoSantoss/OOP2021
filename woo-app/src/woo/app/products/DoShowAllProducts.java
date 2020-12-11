package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                                                                                                                                    
import woo.Storefront;                                                                                                                        

/**
 * Show all products.
 */
public class DoShowAllProducts extends Command<Storefront> {


  public DoShowAllProducts(Storefront receiver) {
    super(Label.SHOW_ALL_PRODUCTS, receiver);
  }

  @Override
  public final void execute() throws DialogException {
    for(var product : _receiver.showAllProducts())
      _display.addLine(product.toString());
    _display.display();
  }

}
