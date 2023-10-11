package xxl.core;

import java.io.Serializable;
import xxl.core.Literal.Literal;

public class Cell implements Serializable {
    private int _row;
    private int _column;
    private Literal _content;

    public Cell() {
        _row = 1;
        _column = 1;
        _content = null;
    }

    public int getRow() {
        return _row;
    }

    public int getColumn() {
        return _column;
    }

    public Literal getContent() {
        return _content;
    }

    public void setContent(Literal c) {
        _content = c;
    }
    public Cell getCell(int row, int column) {
        return new Cell();
    }

    @Override
    public String toString() {
        return "Cell{" + "row=" + _row + ", column=" + _column + '}';
    }

    public Spreadsheet getSpreadsheet() {
        return new Spreadsheet(getRow(), getColumn());
    }
}
