package models;

import models.enums.BookGenre;
import models.enums.Language;

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
}
