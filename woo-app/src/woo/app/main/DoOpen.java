package woo.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import woo.Storefront;
import woo.exceptions.UnavailableFileException;
import woo.app.exceptions.FileOpenFailedException;

/**
 * Open existing saved state.
 */
public class DoOpen extends Command<Storefront> {

  private Input<String> filename;

  /** @param receiver */
  public DoOpen(Storefront receiver) {
    super(Label.OPEN, receiver);
    filename = _form.addStringInput(Message.openFile());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException,FileOpenFailedException {
    _form.parse();
    try {
      _receiver.load(filename.value());
    } 
    catch (UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe.getFilename());
    } 
    catch (ClassNotFoundException e){  
      e.printStackTrace();
    }
    catch(FileNotFoundException e){
      throw new FileOpenFailedException(filename.value());
    }
    catch(IOException e){
      e.printStackTrace();
    }

    
  }

}
