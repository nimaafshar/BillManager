package models;

import javafx.scene.paint.Color;
import models.enums.CPU;
import models.enums.EnergyLabel;

public class Laptop extends ElectricalProduct {
    CPU cpu;
    String gpu;
    public Laptop(ElectricalProduct electricalProduct,CPU CPU,String GPU) {
        super(electricalProduct);
        this.cpu = CPU;
        this.gpu = GPU;
    }
}
