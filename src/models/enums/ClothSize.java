package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum ClothSize {
    S("Small"),
    M("Medium"),
    L("Large"),
    XL("XLarge"),
    XXL("XXLarge");

    private String alias;

    ClothSize(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, ClothSize> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(ClothSize pt : ClothSize.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static ClothSize get(String alias){
        return lookup.get(alias);
    }
}
