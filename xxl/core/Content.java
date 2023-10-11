package xxl.core;

import xxl.core.Literal.Literal;

public abstract class Content {
    // Common methods and properties for content
    public abstract String toString();
    public abstract Literal value();

    public String asString() {
        Literal result = value();
        return result.toString();
    }

    public int asInt() {
        Literal result = value();
        return result.asInt();
    }
}
