package xxl.app.main;

import pt.tecnico.uilib.forms.Field;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.UnavailableFileException;

/**
 * Open existing file.
 */
class DoOpen extends Command<Calculator> {

  DoOpen(Calculator receiver) {
    super(Label.OPEN, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
  
      try {
        Form form = new Form();
      form.addStringField("File", Message.openFile()); // Assuming openFile() returns a file name or path
      form.parse();

      //Retrieve the value of the File
      String filename = form.stringField("File");

      //Call the load method of _receiver to load the file
      _receiver.load(filename);


      } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
      }
  }
}
