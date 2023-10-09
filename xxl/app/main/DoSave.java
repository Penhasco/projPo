package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import xxl.core.Calculator;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSave extends Command<Calculator> {

  DoSave(Calculator receiver) {
    super(Label.SAVE, receiver, xxl -> xxl.getSpreadsheet() != null);
  }
  
  @Override
  protected final void execute() {
    try {
      // Check if there are unsaved changes
      if (!_receiver.isDirty()) {
        return;
      }

      // Check if there's an associated file
      String currentFile = _receiver.getAssociatedFile();
      Form form = new Form(); // Create the form object
      if (currentFile == null) {
        form.addStringField("File", Message.newSaveAs());
        form.parse();
        currentFile = form.stringField("File");
        _receiver.setAssociatedFile(currentFile);
      }

      // Save the current state to the associated file
      _receiver.saveAs(currentFile);

      System.out.println("Saved changes to " + currentFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}