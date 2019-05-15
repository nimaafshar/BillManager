package models;

import javafx.scene.paint.Color;
import models.enums.EnergyLabel;
import org.json.simple.JSONObject;

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

    @Override
    public JSONObject toJSON(){
        JSONObject json = super.toJSON();
        json.put("color",this.color.toString());
        json.put("power",this.power);
        json.put("warranty",this.warranty);
        json.put("energy_label",this.energyLabel.name());
        return json;
    }

    public ElectricalProduct(JSONObject json){
        super(json);
        this.color = Color.valueOf((String)json.get("color"));
        this.power = (long) json.get("power");
        this.warranty = (long) json.get("warranty");
        this.energyLabel = EnergyLabel.valueOf((String)json.get("energy_label"));
    }
}
