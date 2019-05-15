package product;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import models.ElectricalProduct;
import models.enums.EnergyLabel;
import models.enums.ProductType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ElectricalProductMenu implements Initializable {
    public static CreateProductWindow createProductWindow;
    public ComboBox<String> energyLabelCombo;
    public ColorPicker colorPicker;
    public TextField powerField;
    public TextField warrantyField;
    public Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(EnergyLabel energyLabel:EnergyLabel.values()){
            energyLabelCombo.getItems().add(energyLabel.getAlias());
        }
        energyLabelCombo.setValue(energyLabelCombo.getItems().get(0));

        powerField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    powerField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        warrantyField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    warrantyField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        if (createProductWindow.inUseProduct.type == ProductType.TV ||createProductWindow.inUseProduct.type == ProductType.MOBILE ||createProductWindow.inUseProduct.type == ProductType.LAPTOP){
            submitButton.setText("Next");
        }
    }

    public void submit(ActionEvent actionEvent) throws IOException {
        EnergyLabel energyLabel = EnergyLabel.get(energyLabelCombo.getValue());
        Color color = colorPicker.getValue();
        Long power = Long.valueOf(powerField.getText().trim().equals("")? "0":powerField.getText());
        Long warranty = Long.valueOf(warrantyField.getText().trim().equals("")? "0":powerField.getText());
        createProductWindow.inUseProduct = new ElectricalProduct(createProductWindow.inUseProduct,color,power,warranty,energyLabel);
        switch (createProductWindow.inUseProduct.type){
            case ELECTRICAL_PRODUCT:
                createProductWindow.finalProduct.setValue(true);
                break;
            case TV:
                createProductWindow.showTVMenu();
                break;
            case LAPTOP:
                createProductWindow.showLaptopMenu();
                break;
            case MOBILE:
                createProductWindow.showMobileMenu();
                break;
        }
    }
}
