package Menu;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import product.CreateProductWindow;

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

}
