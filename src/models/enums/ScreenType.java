package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum ScreenType {
    LED("LED"),
    LCD("LCD");

    private String alias;

    ScreenType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, ScreenType> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(ScreenType pt : ScreenType.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static ScreenType get(String alias){
        return lookup.get(alias);
    }
}
