package io.teknek.sol;

public class SolHelper {
    public static Fx lit(Object o){
        return new Literal(o);
    }

    public static Equals equal(Fx left, Fx right){
        return new Equals(left, right);
    }

    public static Field field(String name){
        return new Field(name);
    }
}
