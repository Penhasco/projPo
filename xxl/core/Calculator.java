package xxl.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import xxl.core.Literal.Literal;
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
  private boolean _spreadsheetInitialized = false;

  // Constructor
  public Calculator() {
    _user = null;
    _associatedFile = null;
    _dirty = false;
  }

  public final Spreadsheet getSpreadsheet() {
    return _spreadsheet;
  }

  public void setActiveSpreadsheet(Spreadsheet spreadsheet) {
    _spreadsheetInitialized = true;
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
  public void createNewSpreadsheet(int rows, int columns) {
      _spreadsheet = new Spreadsheet(rows, columns);
      _dirty = true;
    }
  // Import data from a text file
    public void importFile(String filename) throws ImportFileException {
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          String line;
          int numRows = -1;
          int numCols = -1;
          while ((line = reader.readLine()) != null) {
            if (line.startsWith("linhas=")) {
              numRows = Integer.parseInt(line.substring(7));
            }
            if (line.startsWith("colunas=")) {
              numCols = Integer.parseInt(line.substring(8));
              System.out.println("Rows: " + numRows);
              System.out.println("Columns: " + numCols);
              setActiveSpreadsheet(_spreadsheet);
            }
            createNewSpreadsheet(numRows, numCols);
            
            if (_spreadsheetInitialized) {
                String[] parts = line.split("[;|]");
                if (parts.length == 3) {
                  int row = Integer.parseInt(parts[0]);
                  int col = Integer.parseInt(parts[1]);
                  double content = Double.parseDouble(parts[2]);
                  Literal content = new Literal(parts[2]);

                  try {
                    _spreadsheet.insertContent(row, col, content);
                  } catch (UnrecognizedEntryException e) {
                    System.out.println("Invalid data format");
                  }
              } 
                else {
                  System.out.println("Invalid data format");
            }
          }
        }
      } catch (IOException e) {
          throw new ImportFileException(filename, e);
      }
  }

  

  

  // Method to create a user
  public boolean createUser(String name) {
    return true;
  }

  public void setSpreadsheet(Spreadsheet newSpreadsheet) {
    _spreadsheet = newSpreadsheet;
    _dirty = true;
  }

}
