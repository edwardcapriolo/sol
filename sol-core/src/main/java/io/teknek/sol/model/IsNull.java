package io.teknek.sol.model;

public class IsNull implements Fx<Boolean> {
    private final Fx inner;
    public IsNull(Fx inner){
        this.inner = inner;
    }

    public Fx getInner() {
        return inner;
    }
}
