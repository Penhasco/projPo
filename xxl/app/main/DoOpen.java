package xxl.app.main;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import xxl.app.exception.FileOpenFailedException;
import xxl.core.Calculator;
import xxl.core.exception.UnavailableFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
      form.addStringField("File", Message.openFile());
      form.parse();

      String filename = form.stringField("File");

      if (isTextFile(filename)) {
        // Handle text file opening - display content 
        handleTextFile(filename);
      } else {
        // Call the load method of _receiver to load the file
        _receiver.load(filename);
      }

    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    } catch (IOException e) {
      // File HI/O errors
      e.printStackTrace();
    }
  }

  private boolean isTextFile(String filename) {
    // Check if the file is a text file based on its extension
    String extension = getFileExtension(filename);
    return extension != null && (extension.equalsIgnoreCase("txt") || extension.equalsIgnoreCase("text"));
  }

  private String getFileExtension(String filename) {
    int dotIndex = filename.lastIndexOf(".");
    if (dotIndex > 0 && dotIndex < filename.length() - 1) {
      return filename.substring(dotIndex + 1);
    }
    return null;
  }

  private void handleTextFile(String filename) throws IOException {
    Path filePath = Path.of(filename);
    String fileContent = Files.readString(filePath);
    System.out.println("Content of the text file:");
    System.out.println(fileContent);
  }
}
