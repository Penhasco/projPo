package xxl.core.Function;

import xxl.core.Literal.Literal;
import xxl.core.Range;

public abstract class IntervalFunction extends Function {
    private Range _associatedRange;

    public IntervalFunction(String name) {
        super(name);
        this._associatedRange = null;
    }

    public void setAssociatedRange(Range range) {
        this._associatedRange = range;
    }

    public Range getAssociatedRange() {
        return this._associatedRange;
    }

    @Override
    public abstract Literal compute();

    public String toString() {
        return "IntervalFunction{" + super._name + '}';
    }
}


