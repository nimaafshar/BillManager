package models.enums;

import java.util.HashMap;
import java.util.Map;

public enum BookGenre {
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science Fiction"),
    ROMANCE("Romance"),
    THRILLER("Thriller"),
    MYSTERY("Mystery");

    private String alias;

    BookGenre(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    private static final Map<String, BookGenre> lookup = new HashMap<>();

    //Populate the lookup table on loading time
    static
    {
        for(BookGenre pt : BookGenre.values())
        {
            lookup.put(pt.getAlias(), pt);
        }
    }

    public static BookGenre get(String alias){
        return lookup.get(alias);
    }
}
