package product;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import models.Cloth;
import models.Shirt;
import models.enums.Sleeve;

import java.net.URL;
import java.util.ResourceBundle;

public class ShirtMenu implements Initializable {
    public ComboBox<String> sleeveCombo;
    public static CreateProductWindow createProductWindow;

    public void submit(ActionEvent actionEvent) {
        Sleeve sleeve = Sleeve.get(sleeveCombo.getValue());
        createProductWindow.inUseProduct = new Shirt((Cloth) createProductWindow.inUseProduct, sleeve);
        createProductWindow.finalProduct.setValue(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Sleeve sleeve:Sleeve.values()){
            sleeveCombo.getItems().add(sleeve.getAlias());
        }
        sleeveCombo.setValue(Sleeve.values()[0].getAlias());
    }


}
