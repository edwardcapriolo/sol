package io.teknek.sol.model;

public class Try<R>  implements Fx<R> {
    private final Fx<R> inner;

    public Try(Fx<R> inner){
        this.inner = inner;
    }

    public Fx<R> getInner() {
        return inner;
    }
}
