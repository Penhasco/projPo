package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Calculator;
import xxl.core.Spreadsheet;



/**
 * Open a new file.
 */
class DoNew extends Command<Calculator> {

  DoNew(Calculator receiver) {
    super(Label.NEW, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    Form form = new Form();
    form.addIntegerField("Lines", "Enter the number of lines:");
    form.addIntegerField("Columns", "Enter the number of columns:");
    form.parse();

    //Gets dimensions from the form fields
    int numRows = form.integerField("Lines");
    int numCols = form.integerField("Columns");

    //Creates new anonymous spreadsheet
    new Spreadsheet(numRows, numCols);
  }
}



