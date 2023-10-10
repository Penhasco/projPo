import java.util.ArrayList;
import java.util.List;

public class CutBuffer {
    private List<Cell> cells;

    public CutBuffer() {
        cells = new ArrayList<>();
    }

    public List<Cell> getCells() {
        return cells;
    }
}
