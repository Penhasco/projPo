package xxl.core;

import xxl.core.Literal.Literal;

public class Reference {
    private int _row;
    private int _column;
    private Cell cell;

    public Reference(int row, int column, Spreadsheet spreadsheet) {
        _row = row;
        _column = column;
        this.cell = cell.getCell(row, column);
    }

    public String toString() {
        return "Reference{_row=" + _row + ", _column=" + _column + '}';
    }

    Literal value() {
        return cell.getContent();
    }
}

