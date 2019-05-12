package product;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import models.Product;
import models.enums.ProductType;

import java.io.IOException;

public class CreateProductWindow {

    public  Product inUseProduct;
    public BooleanProperty finalProduct;

    Stage stage;
    public void start() throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("create_product.fxml"));
        stage.setTitle("Bill Manager - Create Product");
        Scene scene = new Scene(root);
        Button nextButton = (Button)scene.lookup("#nextButton");
        TreeView<String> tree = (TreeView<String>) scene.lookup("#tree");
        Label logLabel = (Label) scene.lookup("#logLabel");
        nextButton.setOnAction(e->{
            TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();
            if(selectedItem == null){
                logLabel.setText("Please Select Something");
            }else {
                logLabel.setText("");
                try {
                    createProductForType(ProductType.get(selectedItem.getValue()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        stage.setScene(scene);
        stage.showAndWait();
    }

    void createProductForType(ProductType productType) throws IOException {
        finalProduct.setValue(false);
        finalProduct.addListener((v,oldValue,newValue)->{
            if(oldValue.booleanValue() == false && newValue.booleanValue() == true) {
                //                    todo:add product to list
            }
        });
        showProductMenu(productType);
    }

    public void showProductMenu(ProductType productType) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("product_menu.fxml"));
        Scene scene = new Scene(root);
        Label typeLabel = (Label)scene.lookup("#typeLabel");
        typeLabel.setText(productType.getAlias());
        stage.setScene(scene);
        ProductMenu.createProductWindow = this;
    }

    public void showClothMenu(){ }
    public void showElectricalMenu(){ }
    public void showBookMenu(){ }
}
