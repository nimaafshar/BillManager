package Menu;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import product.CreateProductWindow;
import product.LaptopMenu;
import product.ShowProducts;

import java.io.IOException;

public class MenuController {
//    @FXML
//    Button createProductButton,
//            showProductsButton,
//            createInvoiceButton,
//            showInvoicesButton,
//            statisticsButton;


    public void showCreateProductMenu() throws IOException {
        new CreateProductWindow().start();
    }

    public void showShowProducts(ActionEvent actionEvent) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../product/show_products.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

    public void showCreateInvoice(ActionEvent actionEvent) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Invoice/create_invoice.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

    public void showStatistics(ActionEvent actionEvent) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../statistics/statistics.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }

    public void showShowInvoices(ActionEvent actionEvent) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Invoice/show_invoices.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
    }
}
