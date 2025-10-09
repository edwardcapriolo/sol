package io.teknek.sol;

import io.teknek.sol.model.*;

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

    public static Field field(String name, Field next){
        return new Field(name, next);
    }

    public static Stringify stringify(Fx inner){
        return new Stringify(inner);
    }

    public static <T> Try<T> wrapWithTry(Fx<T> inner){
        return new Try<>(inner);
    }

}
