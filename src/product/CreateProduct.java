package product;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import models.Product;
import models.enums.ProductType;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateProduct implements Initializable {

    @FXML
    TreeView<String> tree;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> product = new TreeItem<>("Product");
        product.setExpanded(true);
        TreeItem<String> cloth = newItem("Cloth",product);
        cloth.setExpanded(true);
        TreeItem<String> shirt = newItem("Shirt",cloth);
        TreeItem<String> pants = newItem("Pants",cloth);
        TreeItem<String> electricalProduct = newItem("Electrical Product",product);
        electricalProduct.setExpanded(true);
        TreeItem<String> TV = newItem("TV",electricalProduct);
        TreeItem<String> Mobile = newItem("Mobile",electricalProduct);
        TreeItem<String> Laptop = newItem("Laptop",electricalProduct);
        TreeItem<String> book = newItem("Book",product);

        tree.setRoot(product);

    }

    static TreeItem<String> newItem(String text,TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(text);
        parent.getChildren().add(item);
        return item;
    }
}
