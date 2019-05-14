package product;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import models.Cloth;
import models.enums.ClothMaterial;
import models.enums.ClothSize;
import models.enums.ProductType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClothMenu implements Initializable {
    public static CreateProductWindow createProductWindow;
    public ComboBox<String> materialCombo;
    public ComboBox<String> sizeCombo;
    public Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(ClothMaterial material:ClothMaterial.values()){
            materialCombo.getItems().add(material.getAlias());
        }
        materialCombo.setValue(ClothMaterial.values()[0].getAlias());

        for(ClothSize size:ClothSize.values()){
            sizeCombo.getItems().add(size.getAlias());
        }
        sizeCombo.setValue(ClothSize.values()[0].getAlias());
        if (createProductWindow.inUseProduct.type == ProductType.SHIRT ||createProductWindow.inUseProduct.type == ProductType.PANTS){
            submitButton.setText("Next");
        }
    }

    public void submit(ActionEvent actionEvent) throws IOException {
        ClothMaterial clothMaterial = ClothMaterial.get(materialCombo.getValue());
        ClothSize clothSize = ClothSize.get(sizeCombo.getValue());
        createProductWindow.inUseProduct = new Cloth(createProductWindow.inUseProduct,clothMaterial,clothSize);
        switch (createProductWindow.inUseProduct.type){
            case CLOTH:
                createProductWindow.finalProduct.setValue(true);
                break;
            case SHIRT:
                createProductWindow.showShirtMenu();
                break;
            case PANTS:
                createProductWindow.showPantsMenu();
                break;
        }
    }
}
