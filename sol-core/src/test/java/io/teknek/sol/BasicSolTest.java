package io.teknek.sol;

import io.teknek.sol.model.Equals;
import io.teknek.sol.model.Literal;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import static io.teknek.sol.SolHelper.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicSolTest {
    Sol sol = new Sol();
    @Test
    void basicLiteralTest() {
        assertTrue(
                sol.compile(
                    equal(lit(5L), lit(5L)))
                        .apply(null));
        {
            Function<Long, Boolean> simpleSol = sol.compile(new Equals(new Literal(5L), new Literal(5L)));
            assertTrue(simpleSol.apply(null));
        }
        {
            Function<Long, Boolean> simpleSol2 = sol.compile(new Equals(new Literal(5L), new Literal("5")));
            assertFalse(simpleSol2.apply(null));
        }
        {
            Function<Long, Boolean> simpleSol = sol.compile(new Equals(new Literal(5L), new Literal(5)));
            assertFalse(simpleSol.apply(null));
        }
    }
}
