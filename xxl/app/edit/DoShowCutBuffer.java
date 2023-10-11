package xxl.app.edit;

import pt.tecnico.uilib.menus.Command;
import xxl.core.Cell;
import xxl.core.Spreadsheet;


/**
 * Show cut buffer command.
 */

  class DoShowCutBuffer extends Command<Spreadsheet> {

    DoShowCutBuffer(Spreadsheet receiver) {
      super(Label.SHOW_CUT_BUFFER, receiver);
    }

    @Override
    protected final void execute() {
      Spreadsheet.CutBuffer cutBuffer = _receiver.getCutBuffer();

      // Check if the cut buffer is empty
      if (cutBuffer.isEmpty()) {
        System.out.println("Cut buffer is empty.");
      } else {
        System.out.println("Contents of the cut buffer:");
        for (Cell cell : cutBuffer.getCells()) {
          System.out.println(cell);
        }
      }
    }
  }

