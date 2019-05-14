package product;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.ElectricalProduct;
import models.Mobile;
import models.enums.MobileOS;

import java.net.URL;
import java.util.ResourceBundle;

public class MobileMenu implements Initializable {
    public static CreateProductWindow createProductWindow;
    public ComboBox<String> OSCombo;
    public TextField storageField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(MobileOS os:MobileOS.values()){
            OSCombo.getItems().add(os.getAlias());
        }
        OSCombo.setValue(OSCombo.getItems().get(0));
        storageField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    storageField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void submit(ActionEvent actionEvent) {
        MobileOS os = MobileOS.get(OSCombo.getValue());
        int storage= Integer.valueOf(storageField.getText().trim().equals("")? "0":storageField.getText());
        createProductWindow.inUseProduct = new Mobile((ElectricalProduct)createProductWindow.inUseProduct,os,storage);
        createProductWindow.finalProduct.setValue(true);
    }
}
