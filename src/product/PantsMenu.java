package product;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import models.Cloth;
import models.Pants;
import models.enums.FitType;

import java.net.URL;
import java.util.ResourceBundle;

public class PantsMenu implements Initializable {
    public static CreateProductWindow createProductWindow;
    public ComboBox<String> fitTypeCombo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(FitType fitType:FitType.values()){
            fitTypeCombo.getItems().add(fitType.getAlias());
        }
        fitTypeCombo.setValue(fitTypeCombo.getItems().get(0));
    }

    public void submit(ActionEvent actionEvent) {
        FitType fitType = FitType.get(fitTypeCombo.getValue());
        createProductWindow.inUseProduct = new Pants((Cloth) createProductWindow.inUseProduct, fitType);
        createProductWindow.finalProduct.setValue(true);
    }
}
