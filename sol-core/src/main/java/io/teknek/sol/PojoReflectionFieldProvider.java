package io.teknek.sol;

public class PojoReflectionFieldProvider implements SolFieldProvider{
    @Override
    public Object provideField(Object input, String field) {
        try {
            java.lang.reflect.Field inputField = input.getClass().getField(field);
            return inputField.get(input);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
