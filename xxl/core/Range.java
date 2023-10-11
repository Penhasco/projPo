package xxl.core;
import java.util.ArrayList;
import java.util.List;

public class Range {
    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;
    private Cell _cells;
    

    public Range(int beginRow, int beginColumn, int endRow, int endColumn){
        _beginRow = beginRow;
        _beginColumn = beginColumn;
        _endRow = endRow;
        _endColumn = endColumn;
    }

    public List<Cell> getCells() {
        List<Cell> cells = new ArrayList<>();
        for (int row = _beginRow; row <= _endRow; row++) {
            for (int col = _beginColumn; col <= _endColumn; col++) {
                if (_cells != null) {
                    Cell cell = _cells.getCell(row, col);
                    if (cell != null) {
                        cells.add(cell);
                    }
                }
            }
        }
        return cells;  
    }

    public Spreadsheet getSpreadsheet() {
        return _cells.getSpreadsheet();
    }
}
    

    