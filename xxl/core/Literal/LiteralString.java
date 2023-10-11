package xxl.core.Literal;

public class LiteralString extends Literal {
    private String _value;

    public LiteralString(String value) {
        _value = value;
    }

    @Override
    public LiteralString value() {
        return this;
    }

    @Override
    public String toString() {
        return _value;
    }

    public String asString() {
        return _value;
    }

    public int asInt() {
        try {
            return Integer.parseInt(_value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
