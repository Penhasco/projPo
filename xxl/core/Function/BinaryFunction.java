package xxl.core.Function;
import javax.swing.text.AbstractDocument.Content;

import xxl.core.Literal.Literal;


public abstract class BinaryFunction extends Function{
    public BinaryFunction(String name) {
        super(name);
    }

    public abstract Literal compute(Literal[] operands);

    public String toString() {
        return "BinaryFunction{" + _name + '}';
    }

    // Method to interact with two Content objects
    public void interactWithContent(Content content1, Content content2) {
    }
}
