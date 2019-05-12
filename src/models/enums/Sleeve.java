package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum Sleeve {
    LONG("Long"),
    SHORT("Short"),
    NONE("None");

    private String alias;

    Sleeve(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, Sleeve> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(Sleeve pt : Sleeve.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static Sleeve get(String alias){
        return lookup.get(alias);
    }
}
