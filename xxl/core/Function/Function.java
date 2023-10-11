package xxl.core.Function;
import xxl.core.Content;
import xxl.core.Literal.Literal;

public abstract class Function extends Content {
    protected String _name;

    public Function(String name) {
        _name = name;
    }

    public abstract Literal compute();

    public String asString() {
        Literal result = compute();
        return result.toString();
    }

    public int asInt() {
        Literal result = compute();
        return result.asInt();
    }

    public Literal value() {
        return compute();
    }
}
