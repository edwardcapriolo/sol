package io.teknek.sol.model;

public class Field implements Fx {
    private final String name;
    private final Field next;

    public Field(String name) {
        this.name = name;
        this.next = null;
    }

    public Field(String name, Field nested){
        this.next = nested;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public Field getNext() {
        return next;
    }
}
