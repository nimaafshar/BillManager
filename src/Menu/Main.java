package Menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Product;
import models.enums.Brand;
import models.enums.ProductType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Bill Manager - Menu");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Product product = new Product(1,
                "pr1",
                100,
                ProductType.BOOK,
                Brand.APPLE,
                "somet description");
        String jsonString = product.toJSON().toJSONString();
        System.out.println(jsonString);
        try {
            JSONObject obj = (JSONObject) new JSONParser().parse(jsonString);
            Product pr2 = new Product(obj);
            System.out.println(pr2.toJSON().toJSONString());
        } catch (ParseException e) {
            System.out.println("parse exception");
        }
//        launch(args);
    }
}
