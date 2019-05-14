package product;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.ElectricalProduct;
import models.Product;
import models.enums.Brand;
import models.enums.ProductType;

import javax.security.auth.login.CredentialException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductMenu implements Initializable {
    static CreateProductWindow createProductWindow;
    public TextField buyPriceField;
    public TextField idField;
    public ComboBox<String> brandCombo;
    public Button submitButton;
    public TextField nameField;
    public Label typeLabel;
    public TextArea descriptionField;
    public static ProductType productType;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Brand brand:Brand.values()){
            brandCombo.getItems().add(brand.name());
        }
        brandCombo.setValue(Brand.values()[0].name());
        buyPriceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    buyPriceField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        idField.setText(String.valueOf(Product.last_id+1));
        if(productType != ProductType.PRODUCT){
            submitButton.setText("Next");
        }
    }


    public void submit(ActionEvent actionEvent) throws IOException {
        long id = Product.last_id+1;
        String name = nameField.getText();
        long buyPrice = Long.valueOf(buyPriceField.getText().trim().equals("") ? "0" : buyPriceField.getText());
        ProductType type = ProductType.get(typeLabel.getText().trim().equals("") ? ProductType.PRODUCT.getAlias():typeLabel.getText());
        Brand brand = Brand.get(brandCombo.getValue());
        String description = descriptionField.getText();
        createProductWindow.inUseProduct = new Product(id,name,buyPrice,type,brand,description);
        switch (type){
            case PRODUCT:
                createProductWindow.finalProduct.setValue(true);
                break;
            case CLOTH:
                createProductWindow.showClothMenu();
            case SHIRT:
                createProductWindow.showClothMenu();
                break;
            case PANTS:
                createProductWindow.showClothMenu();
                break;
            case ELECTRICAL_PRODUCT:
                createProductWindow.showElectricalMenu();
                break;
            case TV:
                createProductWindow.showElectricalMenu();
                break;
            case MOBILE:
                createProductWindow.showElectricalMenu();
                break;
            case LAPTOP:
                createProductWindow.showElectricalMenu();
                break;
            case BOOK:
                createProductWindow.showBookMenu();
                break;
        }

    }
}
