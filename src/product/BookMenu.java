package product;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Book;
import models.enums.BookGenre;
import models.enums.Language;

import java.net.URL;
import java.util.ResourceBundle;

public class BookMenu implements Initializable {
    public static CreateProductWindow createProductWindow;
    public ComboBox<String> languageCombo;
    public ComboBox<String> genreCombo;
    public TextField publisherField;
    public TextField authorField;

    public void submit(ActionEvent actionEvent) {
        Language language = Language.get(languageCombo.getValue());
        BookGenre genre = BookGenre.get(genreCombo.getValue());
        String publisher = publisherField.getText();
        String author = authorField.getText();
        createProductWindow.inUseProduct = new Book(createProductWindow.inUseProduct,genre,language,author,publisher);
        createProductWindow.finalProduct.setValue(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Language language:Language.values()){
            languageCombo.getItems().add(language.getAlias());
        }
        languageCombo.setValue(languageCombo.getItems().get(0));
        for(BookGenre bookGenre:BookGenre.values()){
            genreCombo.getItems().add(bookGenre.getAlias());
        }
        genreCombo.setValue(genreCombo.getItems().get(0));
    }
}
