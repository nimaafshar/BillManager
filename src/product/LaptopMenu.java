package product;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.ElectricalProduct;
import models.Laptop;
import models.enums.CPU;

import java.net.URL;
import java.util.ResourceBundle;

public class LaptopMenu implements Initializable {
    public static CreateProductWindow createProductWindow;
    public ComboBox<String> CPUCombo;
    public TextField GPUField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(CPU cpu:CPU.values()){
            CPUCombo.getItems().add(cpu.getAlias());
        }
        CPUCombo.setValue(CPUCombo.getItems().get(0));
    }

    public void submit(ActionEvent actionEvent) {
        CPU cpu = CPU.get(CPUCombo.getValue());
        String gpu = GPUField.getText();
        try {
            createProductWindow.inUseProduct = new Laptop((ElectricalProduct) createProductWindow.inUseProduct, cpu, gpu);
        }catch (NullPointerException e){
            System.out.println(cpu);
            System.out.println(gpu);
        }
        createProductWindow.finalProduct.setValue(true);
    }
}
