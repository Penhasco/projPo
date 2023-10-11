package xxl.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import xxl.core.User.User;
import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;

public class Calculator {
  private Spreadsheet _spreadsheet;
  private User _user;
  private String _associatedFile;
  private boolean _dirty;

  // Constructor
  public Calculator() {
    _spreadsheet = null;
    _user = null;
    _associatedFile = null;
    _dirty = false;
  }

  // Getter for the current spreadsheet
  public final Spreadsheet getSpreadsheet() {
    return _spreadsheet;
  }

  // Getter for the user
  public final User getUser() {
    return _user;
  }

  // Setter for associating a file
  public void setAssociatedFile(String filename) {
    _associatedFile = filename;
  }

  // Getter for the associated file
  public String getAssociatedFile() {
    return _associatedFile;
  }

  // Getter for checking if changes have been made
  public boolean isDirty() {
    return _dirty;
  }

  // Setter for marking the application as dirty
  public void setDirty(boolean dirty) {
    _dirty = dirty;
  }

  // Save application state
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_associatedFile == null) {
      throw new MissingFileAssociationException();
    }
    saveAs(_associatedFile);
  }

  // Save application state to a specified file
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_spreadsheet == null) {
      throw new MissingFileAssociationException();
    }
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
      oos.writeObject(this); 
    } catch (FileNotFoundException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }

    _associatedFile = filename;
    _dirty = false;
  }

  // Load application state from a file
  public void load(String filename) throws UnavailableFileException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      Calculator loadedCalculator = (Calculator) ois.readObject();
      this._spreadsheet = loadedCalculator._spreadsheet;
      this._user = loadedCalculator._user;
      this._associatedFile = loadedCalculator._associatedFile;
      this._dirty = loadedCalculator._dirty;
    } catch (FileNotFoundException e) {
      throw new UnavailableFileException(filename);
    } catch (IOException e) {
      throw new UnavailableFileException(filename);
    } catch (ClassNotFoundException e) {
      throw new UnavailableFileException(filename);
    }
  }

  // Import data from a text file
  public void importFile(String filename) throws ImportFileException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      int numRows = Integer.parseInt(reader.readLine());
      int numCols = Integer.parseInt(reader.readLine());
      _spreadsheet = new Spreadsheet(numRows, numCols);
      _dirty = true;

      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split("\\|");
        if (parts.length == 2) {
          String[] position = parts[0].split(";");
          int row = Integer.parseInt(position[0]);
          int col = Integer.parseInt(position[1]);
          String content = parts[1];
          _spreadsheet.insertContent(row, col, content);
        }
      }
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  }

  // Method to create a user
  public boolean createUser(String name) {
    return true;
  }

  // Create a new spreadsheet with the given dimensions
  public void createNewSpreadsheet(int rows, int columns) {
    _spreadsheet = new Spreadsheet(rows, columns);
    _dirty = true;
  }

  public void setSpreadsheet(Spreadsheet newSpreadsheet) {
    _spreadsheet = newSpreadsheet;
    _dirty = true;
  }
}
