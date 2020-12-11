package woo.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import pt.tecnico.po.ui.Command;                                                                                                              
import pt.tecnico.po.ui.DialogException;                                                                                                      
import pt.tecnico.po.ui.Input;                                                                                                                
import woo.Storefront;                                                                                                                        
import woo.exceptions.MissingFileAssociationException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<Storefront> {
  
  private Input<String> filename;

  public DoSave(Storefront receiver) {
    super(Label.SAVE, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException{
    try{
      _receiver.save();                              
    } catch(MissingFileAssociationException e){
      try{
        filename = _form.addStringInput(Message.newSaveAs());
        _form.parse();
        _receiver.saveAs(filename.value());
      } catch(FileNotFoundException ex){
        ex.printStackTrace();
      } catch(IOException ex){
        ex.printStackTrace();
      } catch(MissingFileAssociationException ex){
        ex.printStackTrace();
      }
    } catch(FileNotFoundException e){
      e.printStackTrace();
    } catch(IOException e){
      e.printStackTrace();
    }
  }
}
