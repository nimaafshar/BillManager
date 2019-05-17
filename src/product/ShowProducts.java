package product;

import helper.ProductManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowProducts implements Initializable {
    public TableView<Product> productsTable;
    public ProductManager productManager = new ProductManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Product,Long> idColumn = new TableColumn<>("#");
        idColumn.setMinWidth(20);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product,Long> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(150);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("buy_price"));

        TableColumn<Product,String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(150);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Product,String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setMinWidth(150);
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        productsTable.setItems(this.getProducts());
//        productsTable.getColumns().clear();
        productsTable.getColumns().addAll(idColumn,nameColumn,priceColumn,typeColumn,brandColumn);

    }

    public ObservableList<Product> getProducts(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.addAll(productManager.products);
        return products;
    }

    public void deleteSelectedItems(ActionEvent actionEvent) {
        Product pr = productsTable.getSelectionModel().getSelectedItem();
        this.productManager.removeProduct(pr);
        productsTable.getItems().remove(pr);
    }


}
