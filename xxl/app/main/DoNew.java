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

    //dimensions
    int numRows = form.integerField("Lines");
    int numCols = form.integerField("Columns");

    // Validate input
    if (numRows <= 0 || numCols <= 0) {
        System.out.println("Invalid input: Rows and columns must be positive.");
        return; // Exit without creating a new spreadsheet
    }

    Spreadsheet newSpreadsheet = new Spreadsheet(numRows, numCols);
    _receiver.setSpreadsheet(newSpreadsheet);

  }
}



