package io.teknek.sol;

import io.teknek.sol.model.Try;
import io.teknek.sol.testclasses.SomePojo;
import org.junit.jupiter.api.Test;

import static io.teknek.sol.SolHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TryTest {
    Sol s = new Sol();

    @Test
    void tryExample(){
        SomePojo p = new SomePojo();
        assertNull(s.compile( new Try<String>(field("bla"))).apply(p));
        assertEquals("sara", s.compile(wrapWithTry(field("username"))).apply(p));
    }
}
