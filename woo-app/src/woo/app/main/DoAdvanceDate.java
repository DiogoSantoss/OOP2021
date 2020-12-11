package woo.app.main;

import pt.tecnico.po.ui.Command;  
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;                                                                                                                        
import woo.app.exceptions.InvalidDateException;
import woo.exceptions.IncorrectDateException;

/**
 * Advance current date.
 */
public class DoAdvanceDate extends Command<Storefront> {
  
  private Input<Integer> days;

  public DoAdvanceDate(Storefront receiver) {
    super(Label.ADVANCE_DATE, receiver);
    days = _form.addIntegerInput(Message.requestDaysToAdvance());
  }

  @Override
  public final void execute() throws DialogException, InvalidDateException {
    _form.parse();
    try {
      _receiver.advanceDate(days.value());
    }
    catch (IncorrectDateException e){
      throw new InvalidDateException(e.getDays());
    }
  }
}
