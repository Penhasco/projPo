package xxl.core.Literal;

public class LiteralInteger extends Literal {
    private int _value;

    public LiteralInteger(int value) {
        _value = value;
    }

    @Override
    public LiteralInteger value() {
        return this;
    }

    @Override
    public String toString() {
        return Integer.toString(_value);
    }

    public String asString() {
        return Integer.toString(_value);
    }

    public int asInt() {
        return _value;
    }
}
