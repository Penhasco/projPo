package xxl.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import xxl.core.exception.ImportFileException;
import xxl.core.exception.MissingFileAssociationException;
import xxl.core.exception.UnavailableFileException;
import xxl.core.exception.UnrecognizedEntryException;
import xxl.core.User;
import xxl.core.Spreadsheet;

/**
 * Class representing a spreadsheet application.
 */
public class Calculator {
  /** The current spreadsheet. */
  private Spreadsheet _spreadsheet;
  private User _user;
  private String _associatedFile;
  private boolean _dirty;
  
  // FIXME add more fields and methods if needed
  
  /**
   * Return the current spreadsheet.
   *
   * @returns the current spreadsheet of this application. This reference can be null.
   */
  public Calculator() {
    _spreadsheet = null;
    _user = null;
    _associatedFile = null;
    _dirty = false;
  }

  public final Spreadsheet getSpreadsheet() {
    return _spreadsheet;
  }

  public final User getUser(){
    return _user;
  }

  public void setAssociatedFile(String filename) {
    _associatedFile = filename;
  }

  public String getAssociatedFile() {
    return _associatedFile;
  }

  public boolean isDirty() {
    return _dirty;
  }

  public void setDirty(boolean dirty) {
    _dirty = dirty;
  }

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_associatedFile == null) {
      throw new MissingFileAssociationException();
    }
    saveAs(_associatedFile);


  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_spreadsheet == null) {
        throw new MissingFileAssociationException();
    }
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
        oos.writeObject(this); // Serialize the Calculator instance
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

  
  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   */
  public void load(String filename) throws UnavailableFileException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Calculator loadedCalculator = (Calculator) ois.readObject(); // Deserialize the Calculator instance
            // Copy the state of the loaded calculator to this calculator
            this._spreadsheet = loadedCalculator._spreadsheet;
            this._user = loadedCalculator._user;
            this._associatedFile = loadedCalculator._associatedFile;
            this._dirty = loadedCalculator._dirty;
        }
        catch (FileNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
        catch (IOException e) {
            throw new UnavailableFileException(filename);
        }
        catch (ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
  }
  
  /**
   * Read text input file and create domain entities.
   *
   * @param filename name of the text input file
   * @throws ImportFileException
   */
  public void importFile(String filename) throws ImportFileException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      int numrows = Integer.parseInt(reader.readLine());
      // FIXME open import file and feed entries to new spreadsheet (in a cycle)
      //       each entry is inserted using insertContent of Spreadsheet. Set new
      // spreadsheet as the active one.
      // ....
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }

  }
  
  public boolean createUser(String name){
    return true;
  }

  public void createNewSpreadsheet(int rows, int columns){
    _spreadsheet = new Spreadsheet(rows, columns);
    _dirty = true;
  }
}
