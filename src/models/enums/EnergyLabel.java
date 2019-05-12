package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnergyLabel {
    APLUSPLUSPLUS("A+++"),
    APLUSPLUS("A++"),
    APLUS("A+"),
    A("A"),
    B("B"),
    C("C"),
    D("D");

    private String alias;

    EnergyLabel(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, EnergyLabel> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(EnergyLabel pt : EnergyLabel.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static EnergyLabel get(String alias){
        return lookup.get(alias);
    }
}
