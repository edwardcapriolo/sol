package io.teknek.sol;

public class Equals implements Fx<Boolean> {
    private final Fx left;
    private final Fx right;
    public Equals(Fx left, Fx right){
        this.left = left;
        this.right = right;
    }

    public Fx getLeft() {
        return left;
    }

    public Fx getRight() {
        return right;
    }
}
