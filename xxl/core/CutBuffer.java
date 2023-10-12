package xxl.core;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CutBuffer implements Serializable {
    private List<Cell> cells = new ArrayList<>();

    public List<Cell> getCells() {
        return cells;
    }

    public boolean isEmpty() {
        return cells.isEmpty();
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }
}
