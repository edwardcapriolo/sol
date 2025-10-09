package io.teknek.sol;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static io.teknek.sol.SolHelper.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class StringifTest {
    Sol sol = new Sol();
    @Test
    void testString(){
        {
            Function<Object, Boolean> d = sol.compile(equal(lit("5"), lit(5L)));
            assertFalse(d.apply(null));
        }
        // we havent build the casting into the language YET so poor mans cast!
        {
            Function<Object, Boolean> d = sol.compile(equal(lit("5"), stringify(lit(5L))));
            assertTrue(d.apply(null));
        }

    }

    @Test
    void supplierTest(){
        assertFalse(sol.compileAsSupplier(equal(lit("5"), lit(5L))).get());
    }
}
