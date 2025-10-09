package io.teknek.sol;

import io.teknek.sol.model.*;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class Sol {
    private final Map<Class<?>, SolFieldProvider> providers;
    private final SolFieldProvider defaultProvider = new PojoReflectionFieldProvider();

    public Sol() {
        providers = Collections.emptyMap();
    }

    public Sol(Map<Class<?>, SolFieldProvider> providers){
        this.providers = Map.copyOf(providers);
    }

    public <R> Supplier<R> compileAsSupplier(Fx<R> fx) {
        //not efficient but more charming to the caller
        Function<Object, R> z = compile(fx);
        return () -> z.apply(null);
    }

    public <T, R> Function<T, R> compile(Fx<R> fx) {
        switch (fx) {
            case Literal l:
                return (T _) -> (R) l.getLit();
            case Equals e: {
                return (Function<T, R>) compileEquals(e);
            }
            case Field f: {
                return compileField(f);
            }
            case Stringify s: {
                return (Function<T, R>) compileStringify(s);
            }
            case IsNull isNull: {
                return (Function<T, R>) compileIsNull(isNull);
            }
            case Try<R> t: {
                return compileTry(t);
            }
            default:
                throw new IllegalStateException("Unexpected value: " + fx);
        }
    }

    protected <T, R> Function<T, R> compileTry(Try<R> t) {
        Function<T, R> inner = compile(t.getInner());
        return (T input) -> {
            try {
                return inner.apply(input);
            } catch (RuntimeException e) {
                return null;
            }
        };

    }

    protected <T, R> Function<T, Boolean> compileIsNull(IsNull isNull) {
        Function<T, R> inner = compile(isNull.getInner());
        return (T input) -> inner.apply(input) == null;
    }

    protected <T, R> Function<T, String> compileStringify(Stringify s) {
        Function<T, R> inner = compile(s.getInner());
        return (T input) -> inner.apply(input).toString();
    }

    protected <T, R> Function<T, Boolean> compileEquals(Equals e) {
        Function<T, R> left = compile(e.getLeft());
        Function<T, R> right = compile(e.getRight());
        return (T input) -> {
            R leftResult = left.apply(input);
            if (leftResult == null) {
                return Boolean.FALSE;
            }
            R rightResult = right.apply(input);
            if (rightResult == null) {
                return Boolean.FALSE;
            }
            return Boolean.valueOf(leftResult.equals(rightResult));
        };
    }

    protected <T, R> Function<T, R> compileField(Field f) {
        //we wont split on . for now lets keep it simple
        return (T input) -> {
            if (f.getNext() == null) {
                return (R) providers.getOrDefault(input.getClass(), defaultProvider).provideField(input, f.getName());
            } else {
                Object o = providers.getOrDefault(input.getClass(), defaultProvider).provideField(input, f.getName());
                Function z = compile(f.getNext());
                return (R) z.apply(o);
            }
        };
    }
}
