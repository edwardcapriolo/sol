package io.teknek.sol;

import io.teknek.sol.model.Equals;
import io.teknek.sol.model.Field;
import io.teknek.sol.model.Fx;
import io.teknek.sol.model.Literal;

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
