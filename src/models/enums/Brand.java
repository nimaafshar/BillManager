package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum Brand{
    SAMSUNG("Samsung"),
    APPLE("Apple"),
    NOKIA("Nokia"),
    LG("Lg"),
    HTC("Htc"),
    BOOK("Book");
    private String alias;

    Brand(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, Brand> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(Brand pt : Brand.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static Brand get(String alias){
        return lookup.get(alias);
    }
}
