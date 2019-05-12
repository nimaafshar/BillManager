package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum ClothMaterial {
    COTTON("Cotton"),
    JEAN("Jean"),
    Linen("Linen");

    private String alias;

    ClothMaterial(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, ClothMaterial> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(ClothMaterial pt : ClothMaterial.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static ClothMaterial get(String alias){
        return lookup.get(alias);
    }
}
