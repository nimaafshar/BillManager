package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum FitType {
    SKINNY("Skinny"),
    SLIM("Slim"),
    REGULAR("Regular"),
    RELAXED("Relaxed"),
    LOOSE("Loose");

    private String alias;

    FitType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, FitType> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(FitType pt : FitType.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static FitType get(String alias){
        return lookup.get(alias);
    }
}
