package xxl.core;

import java.io.Serializable;
import java.util.List;

import xxl.core.Literal.Literal;
import xxl.core.User.User;
import xxl.core.exception.UnrecognizedEntryException;


/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {
    private static final long serialVersionUID = 202308312359L;

    private int _rows;
    private int _columns;
    private boolean _changed;
    private CutBuffer _cutBuffer;

    public Spreadsheet(int rows, int columns) {
        _rows = rows;
        _columns = columns;
        _changed = false;
    }

    public int getRows() {
        return _rows;
    }

    public int getColumns() {
        return _columns;
    }

    public boolean isChanged() {
        return _changed;
    }

    public void setChanged(boolean changed) {
        _changed = changed;
    }



    public void insertContent(int row, int column, String contentSpecification) throws UnrecognizedEntryException {
        // Implement content insertion here, handle exceptions.
    }

    public CutBuffer getCutBuffer() {
        return _cutBuffer;
    }

    public void copy(String range) {
        // Implement copy operation using the specified range.
    }

    public void clear(String range) {
        // Implement clear operation using the specified range.
    }

    public void addUser(User u) {
        // Implement adding a user to the spreadsheet.
    }

    public class CutBuffer {
        private List<Cell> cells;

        public List<Cell> getCells() {
            return cells;
    }

        public boolean isEmpty() {
            return false;
        }
    }

    public List<Literal> getCellsValues(Range range) {
        return null;
    }

    public Spreadsheet getSpreadsheet() {
        return null;
    }

    public Cell getCell(int row, int col) {
        return null;
    }

    
}