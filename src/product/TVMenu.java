package product;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import models.ElectricalProduct;
import models.TV;
import models.enums.ScreenType;

import java.net.URL;
import java.util.ResourceBundle;

public class TVMenu implements Initializable {
    public static CreateProductWindow createProductWindow;
    public ComboBox<String> screenTypeCombo;
    public CheckBox homeCinemaCheckBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(ScreenType screenType:ScreenType.values()){
            screenTypeCombo.getItems().add(screenType.getAlias());
        }
        screenTypeCombo.setValue(screenTypeCombo.getItems().get(0));
    }

    public void submit(ActionEvent actionEvent) {
        ScreenType screenType = ScreenType.get(screenTypeCombo.getValue());
        Boolean hasHomeCinema = homeCinemaCheckBox.isSelected();
        createProductWindow.inUseProduct = new TV((ElectricalProduct)createProductWindow.inUseProduct,screenType,hasHomeCinema);
        createProductWindow.finalProduct.setValue(true);
    }
}
