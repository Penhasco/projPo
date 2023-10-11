package xxl.core.Function.Operations;

import xxl.core.Literal.Literal;
import xxl.core.Literal.LiteralString;
import xxl.core.Range;
import xxl.core.Spreadsheet;
import xxl.core.Function.IntervalFunction;

import java.util.List;

public class Concat extends IntervalFunction {
    public Concat() {
        super("Concat"); // Set the name for the Concat function
    }

    @Override
    public Literal compute() {
        List<Literal> cellValues = getCellsValues();

        StringBuilder concatenatedValue = new StringBuilder();
        boolean foundString = false;

        for (Literal value : cellValues) {
            if (value instanceof LiteralString) {
                concatenatedValue.append(value.asString());
                foundString = true;
            }
        }

        if (foundString) {
            return new LiteralString(concatenatedValue.toString());
        } else {
            return new LiteralString(""); // Empty string if no valid string values found
        }
    }

    private List<Literal> getCellsValues() {
        Spreadsheet spreadsheet = getSpreadsheet();
        Range range = getAssociatedRange();
        List<Literal> cellValues = spreadsheet.getCellsValues(range);

        return cellValues;
    }

    private Spreadsheet getSpreadsheet() {
        Range range = getAssociatedRange();
        Spreadsheet spreadsheet = range.getSpreadsheet();

        return spreadsheet;
    }

}
