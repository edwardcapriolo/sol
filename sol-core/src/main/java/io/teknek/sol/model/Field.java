package io.teknek.sol.model;

public class Field implements Fx {
    private final String name;

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
