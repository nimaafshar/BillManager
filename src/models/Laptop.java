package models;

import javafx.scene.paint.Color;
import models.enums.CPU;
import models.enums.EnergyLabel;
import org.json.simple.JSONObject;

public class Laptop extends ElectricalProduct {
    CPU cpu;
    String gpu;
    public Laptop(ElectricalProduct electricalProduct,CPU CPU,String GPU) {
        super(electricalProduct);
        this.cpu = CPU;
        this.gpu = GPU;
    }

    @Override
    public JSONObject toJSON(){
        JSONObject json = super.toJSON();
        json.put("cpu",this.cpu.name());
        json.put("gpu",this.gpu);
        return json;
    }

    public Laptop(JSONObject json){
        super(json);
        this.cpu = CPU.valueOf((String)json.get("cpu"));
        this.gpu = (String)json.get("gpu");
    }
}
