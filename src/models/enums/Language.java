package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum Language {
    PERSIAN("Persian"),
    ENGLISH("English"),
    FRENCH("French");
    private String alias;

    Language(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, Language> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(Language pt : Language.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static Language get(String alias){
        return lookup.get(alias);
    }
}
