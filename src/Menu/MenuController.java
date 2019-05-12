package Menu;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import product.CreateProductWindow;

public class MenuController {

    @FXML
    Button createProductButton,
            showProductsButton,
            createInvoiceButton,
            showInvoicesButton,
            statisticsButton;


    public void showCreateProductMenu(){
        CreateProductWindow.display();

    }

}
