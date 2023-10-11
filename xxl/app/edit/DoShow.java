package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.core.Spreadsheet;
import xxl.core.Literal.Literal;
import pt.tecnico.uilib.forms.Form;
import xxl.core.Cell;

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

    Form form = new Form();
    form.addIntegerField("Start Row", "Enter the starting row:");
    form.addIntegerField("Start Column", "Enter the starting column:");
    form.addIntegerField("End Row", "Enter the ending row:");
    form.addIntegerField("End Column", "Enter the ending column:");
    form.parse();

    int startRow = form.integerField("Start Row");
    int startCol = form.integerField("Start Column");
    int endRow = form.integerField("End Row");
    int endCol = form.integerField("End Column");

    for (int row = startRow; row <= endRow; row++) {
      for (int col = startCol; col <= endCol; col++) {
        Cell cell = sheet.getCell(row, col);
        if (cell != null && cell.getContent() != null) {
          Literal value = cell.getContent();


          String content = formatCell(row, col, value);
          System.out.println(content);
        }
      }
    }
  }

  private String formatCell(int row, int col, Literal value) {
    StringBuilder sb = new StringBuilder();
    sb.append(row).append(";").append(col).append("|");
    if (value.Literal()) {
      sb.append(value.Literal());
    } else {
      sb.append(value.toString());
    }
    return sb.toString();
  }
}

