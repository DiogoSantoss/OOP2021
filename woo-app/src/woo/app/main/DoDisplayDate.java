package woo.app.main;

import pt.tecnico.po.ui.Command;                                                                                                             
import pt.tecnico.po.ui.DialogException;                                                                                                                                                                                                                   
import woo.Storefront;                                                                                                             

/**
 * Show current date.
 */
public class DoDisplayDate extends Command<Storefront> {

  public DoDisplayDate(Storefront receiver) {
    super(Label.SHOW_DATE, receiver);
  }

  @Override
  public final void execute() throws DialogException {
    _display.popup(Message.currentDate(_receiver.displayDate()));
  }
}
