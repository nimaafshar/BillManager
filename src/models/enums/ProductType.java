package models.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public enum ProductType {
    PRODUCT("Product"),
    CLOTH("Cloth"),
    SHIRT("Shirt"),
    PANTS("Pants"),
    ELECTRICAL_PRODUCT("Electrical Product"),
    TV("TV"),
    MOBILE("Mobile"),
    LAPTOP("Laptop"),
    BOOK("Book");


    private String alias;

    ProductType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, ProductType> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(ProductType pt : ProductType.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static ProductType get(String alias){
        return lookup.get(alias);
    }
}
