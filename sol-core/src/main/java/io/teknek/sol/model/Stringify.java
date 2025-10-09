package io.teknek.sol.model;

/**
 * This operator is most useful for simple scalar types such as enums.
 * Use caution when using on complex objects as toString methods are
 * typically used for human debug
 */
public class Stringify implements Fx<String>{
    private final Fx inner;
    public Stringify(Fx inner){
        this.inner = inner;
    }

    public Fx getInner() {
        return inner;
    }
}
