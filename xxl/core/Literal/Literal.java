package xxl.core.Literal;

public abstract class Literal {

    public abstract int asInt();

    public abstract String asString();

    Literal value() {
        return this;
    }

    public boolean Literal() {
        return false;
    }

}
