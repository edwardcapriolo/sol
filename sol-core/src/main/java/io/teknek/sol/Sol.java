package io.teknek.sol;

import java.util.function.Function;

public class Sol {
    public Sol(){

    }
    public <T,R> Function<T,R> compile(Fx<R> fx){
        switch (fx){
            case Literal l: return (T _) ->  (R) l.getLit();
            case Equals e: {
                Function<T,R> left = compile(e.getLeft());
                Function<T,R> right = compile(e.getRight());
                return (T input) -> {
                    R leftResult = left.apply(input);
                    if (leftResult == null){
                        return (R) Boolean.FALSE;
                    }
                    R rightResult = right.apply(input);
                    if (rightResult == null){
                        return (R) Boolean.FALSE;
                    }
                    return (R) Boolean.valueOf(leftResult.equals(rightResult));
                };
            }
            case Field f: {
                //we wont split on . for now lets keep it simple
                return (T input) -> {
                    try {
                        java.lang.reflect.Field inputField = input.getClass().getField(f.getName());
                        return (R) inputField.get(input);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                };
            }
            default:
                throw new IllegalStateException("Unexpected value: " + fx);
        }
    }


}
