package io.teknek.sol;

import io.teknek.sol.model.Fx;
import io.teknek.sol.testclasses.SomeOtherPojo;
import io.teknek.sol.testclasses.SomePojo;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static io.teknek.sol.SolHelper.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FieldTest {

    Sol sol = new Sol();
    @Test
    void simpleReflectionTest(){
        //This is the network serializable function
        Fx<Boolean> isNameSara = equal(field("username"), lit("sara"));
        //compile it
        Function<SomePojo,Boolean> isNameSaraFunction = sol.compile(isNameSara);
        //use it
        assertTrue(isNameSaraFunction.apply(new SomePojo()));
        //one liner more sexy

        assertFalse(sol.compile(
                equal(field("username"), lit("bob"))
            ).apply(new SomePojo()));
    }

    @Test
    void nestedFields(){
        //This is the network serializable function
        Fx<Boolean> isNameSara = equal(field( "somePojo", field("username")), lit("sara"));
        //compile it
        Function<SomeOtherPojo,Boolean> isNameSaraFunction = sol.compile(isNameSara);
        //use it
        assertTrue(isNameSaraFunction.apply(new SomeOtherPojo(this)));

    }
}
