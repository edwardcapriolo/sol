package io.teknek.sol;

public class Literal implements Fx{
    private final Object lit;
    private final String type;

    public Literal(Object lit){
        this.lit = lit;
        this.type = null;
    }
    public Literal(Object lit, String type){
        this.lit = lit;
        this.type = type;
    }

    public Object getLit() {
        return lit;
    }

    public String getType() {
        return type;
    }
}
