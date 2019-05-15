package models;

import models.enums.BookGenre;
import models.enums.Language;
import org.json.simple.JSONObject;

public class Book extends Product {
    String publisher,author;

    public BookGenre genre;
    public Language language;

    public Book(Product product,BookGenre bookGenre,Language language,String author,String publisher) {
        super(product);
        this.genre = bookGenre;
        this.language = language;
        this.publisher = publisher;
        this.author = author;
    }

    @Override
    public JSONObject toJSON(){
        JSONObject json = super.toJSON();
        json.put("publisher",this.publisher);
        json.put("author",this.author);
        json.put("genre",this.genre.name());
        json.put("language",this.language.name());
        return json;
    }

    public Book(JSONObject json){
        super(json);
        this.publisher = (String)json.get("publisher");
        this.author = (String)json.get("author");
        this.genre = BookGenre.valueOf((String)json.get("genre"));
        this.language = Language.valueOf((String)json.get("language"));
    }
}
