package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum MobileOS {
    ANDROID("Android"),
    IOS("Ios"),
    WINDOWS("Windows"),
    BLACKBERRY("Black Berry");

    private String alias;

    MobileOS(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, MobileOS> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(MobileOS pt : MobileOS.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static MobileOS get(String alias){
        return lookup.get(alias);
    }
}
