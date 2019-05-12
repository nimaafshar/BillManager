package models;

import javafx.scene.paint.Color;
import models.enums.EnergyLabel;

public class ElectricalProduct extends Product{
    public Color color;
    public long power;//in wats
    public long warranty;//in days
    public EnergyLabel energyLabel;

    public ElectricalProduct(Product product,Color color,long power,long warranty,EnergyLabel energyLabel) {
        super(product);
        this.color = color;
        this.power = power;
        this.warranty = warranty;
        this.energyLabel =energyLabel;
    }

    public ElectricalProduct(ElectricalProduct electricalProduct){
        super(electricalProduct);
        this.color = electricalProduct.color;
        this.power = electricalProduct.power;
        this.warranty = electricalProduct.warranty;
        this.energyLabel = electricalProduct.energyLabel;
    }
}
