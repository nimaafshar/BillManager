package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum CPU {
    COREI3("Core I3"),
    COREI5("Core I5"),
    COREI7("Core I7"),
    COREI9("Core I9");

    private String alias;

    CPU(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, CPU> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(CPU pt : CPU.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static CPU get(String alias){
        return lookup.get(alias);
    }
}
