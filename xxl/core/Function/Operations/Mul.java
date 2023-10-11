package xxl.core.Function.Operations;

import xxl.core.Function.BinaryFunction;
import xxl.core.Literal.Literal;
import xxl.core.Literal.LiteralInteger;
import xxl.core.Literal.LiteralString;

public class Mul extends BinaryFunction {

    public Mul() {
        super("mul");
    }

    @Override
    public Literal compute(Literal[] operands) {
        if (operands.length != 2) {
            // Check if there are exactly two operands
            throw new IllegalArgumentException("Mul function expects two operands.");
        }

        Literal leftOperand = operands[0];
        Literal rightOperand = operands[1];

        // Perform the multiplication
        if (leftOperand instanceof LiteralString || rightOperand instanceof LiteralString) {
            // If either operand is a string, handle the error
            throw new IllegalArgumentException("Cannot multiply string literals.");
        } else {
            return new LiteralInteger(leftOperand.asInt() * rightOperand.asInt());
        }
    }

    @Override
    public Literal compute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compute'");
    }
}
