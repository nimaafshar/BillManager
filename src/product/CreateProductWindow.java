package product;


import helper.ProductManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    public BooleanProperty finalProduct = new SimpleBooleanProperty();

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
                //                    todo:print product json
                //todo:test this part
                ProductManager.addProduct(this.inUseProduct);
                stage.close();
            }
        });
        showProductMenu(productType);
    }

    public void showProductMenu(ProductType productType) throws IOException {
        ProductMenu.productType = productType;
        Parent root = FXMLLoader.load(getClass().getResource("product_menu.fxml"));
        Scene scene = new Scene(root);
        Label typeLabel = (Label)scene.lookup("#typeLabel");
        typeLabel.setText(productType.getAlias());
        stage.setScene(scene);
        ProductMenu.createProductWindow = this;
    }

    public void showClothMenu() throws IOException {
        ClothMenu.createProductWindow = this;
        Parent root = FXMLLoader.load(getClass().getResource("cloth_menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    public void showElectricalMenu()throws IOException{
        ElectricalProductMenu.createProductWindow = this;
        Parent root = FXMLLoader.load(getClass().getResource("electrical_product_menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void showBookMenu()throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("book_menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        BookMenu.createProductWindow = this;
    }

    public void showShirtMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("shirt_menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        ShirtMenu.createProductWindow = this;
    }
    public void showPantsMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("shirt_menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        PantsMenu.createProductWindow = this;

    }
    public void showTVMenu()throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("tv_menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        TVMenu.createProductWindow = this;
    }
    public void showMobileMenu()throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("mobile_menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        MobileMenu.createProductWindow = this;
    }
    public void showLaptopMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("laptop_menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        MobileMenu.createProductWindow = this;
    }
}
