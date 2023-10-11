package xxl.core.Function.Operations;

import xxl.core.Function.BinaryFunction;
import xxl.core.Literal.Literal;
import xxl.core.Literal.LiteralInteger;
import xxl.core.Literal.LiteralString;

public class Add extends BinaryFunction {

    public Add() {
        super("add");
    }

    @Override
    public Literal compute(Literal[] operands) {
        if (operands.length != 2) {
            // Check if there are exactly two operands
            throw new IllegalArgumentException("Add function expects two operands.");
        }

        Literal leftOperand = operands[0];
        Literal rightOperand = operands[1];

        // Perform the addition
        if (leftOperand instanceof LiteralString || rightOperand instanceof LiteralString) {
            // If either operand is a string, concatenate them
            return new LiteralString(leftOperand.asString() + rightOperand.asString());
        } else {
            return new LiteralInteger(leftOperand.asInt() + rightOperand.asInt());
        }
    }

    @Override
    public Literal compute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compute'");
    }
}
