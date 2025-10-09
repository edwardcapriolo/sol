package io.teknek.sol;

import io.teknek.sol.model.*;

import java.util.function.Function;

public class Sol {
    public Sol(){

    }
    public <T,R> Function<T,R> compile(Fx<R> fx){
        switch (fx){
            case Literal l: return (T _) ->  (R) l.getLit();
            case Equals e: {
                return (Function<T, R>) compileEquals(e);
            }
            case Field f: {
                return compileField(f);
            }
            case Stringify s: {
                return (Function<T, R>) compileStringify(s);
            }
            default:
                throw new IllegalStateException("Unexpected value: " + fx);
        }
    }

    private <T, R> Function<T,String> compileStringify(Stringify s) {
        Function<T,R> inner = compile(s.getInner());
        return (T input) -> inner.apply(input).toString();
    }

    protected <T,R> Function<T,Boolean> compileEquals(Equals e){
        Function<T,R> left = compile(e.getLeft());
        Function<T,R> right = compile(e.getRight());
        return (T input) -> {
            R leftResult = left.apply(input);
            if (leftResult == null){
                return Boolean.FALSE;
            }
            R rightResult = right.apply(input);
            if (rightResult == null){
                return Boolean.FALSE;
            }
            return Boolean.valueOf(leftResult.equals(rightResult));
        };
    }

    protected <T,R> Function<T,R> compileField(Field f){
        //we wont split on . for now lets keep it simple
        return (T input) -> {
            if (f.getNext() == null) {
                try {
                    java.lang.reflect.Field inputField = input.getClass().getField(f.getName());
                    return (R) inputField.get(input);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    java.lang.reflect.Field inputField = input.getClass().getField(f.getName());
                    Object o = inputField.get(input);
                    Function z = compile(f.getNext());
                    return (R) z.apply(o);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
