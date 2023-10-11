package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Cell;
import xxl.core.Spreadsheet;
import pt.tecnico.uilib.forms.Form;

/**
 * Class for searching functions.
 */
class DoShow extends Command<Spreadsheet> {

  DoShow(Spreadsheet receiver) {
    super(Label.SHOW, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    Spreadsheet sheet = _receiver.getSpreadsheet();

    if (sheet == null) {
      System.out.println("No active spreadsheet.");
      return;
    }

    // Create a form to gather user input
    Form form = new Form();
    form.addStringField("range", "Enter the range (e.g., 'startRow;startCol:endRow;endCol'):");

    // Parse user input
    form.parse();

    String range = form.stringField("range");

    if (range == null) {
      System.out.println("No range specified.");
      return;
    }

    String[] rangeParts = range.split(":");
    if (rangeParts.length != 2) {
      System.out.println("Invalid range.");
      return;
    }

    String[] startParts = rangeParts[0].split(";");
    String[] endParts = rangeParts[1].split(";");
    if (startParts.length != 2 || endParts.length != 2) {
      System.out.println("Invalid range.");
      return;
    }

    int startRow = Integer.parseInt(startParts[0]);
    int startCol = Integer.parseInt(startParts[1]);
    int endRow = Integer.parseInt(endParts[0]);
    int endCol = Integer.parseInt(endParts[1]);

    if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0) {
      System.out.println("Invalid range.");
      return;
    }

    if (startRow > endRow || startCol > endCol) {
      System.out.println("Invalid range.");
      return;
    }

    if (endRow >= sheet.getRows() || endCol >= sheet.getColumns()) {
      System.out.println("Invalid range.");
      return;
    }

    for (int row = startRow; row <= endRow; row++) {
      for (int col = startCol; col <= endCol; col++) {
        Cell cell = sheet.getCell(row, col);
        if (cell != null) {
          System.out.println(cell);
        }
      }
    }
  }
}
