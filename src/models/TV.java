package models;

import javafx.scene.paint.Color;
import models.enums.EnergyLabel;
import models.enums.ScreenType;
import org.json.simple.JSONObject;

public class TV extends ElectricalProduct {

    public boolean homeCinema;
    public ScreenType screenType;

    public TV(ElectricalProduct electricalProduct,ScreenType screenType,boolean homeCinema) {
        super(electricalProduct);
        this.screenType = screenType;
        this.homeCinema =homeCinema;
    }

    @Override
    public JSONObject toJSON(){
        JSONObject json = super.toJSON();
        json.put("screen_type",this.screenType.name());
        json.put("has_home_cinema",this.homeCinema);
        return json;
    }

    public TV(JSONObject json){
        super(json);
        this.screenType = ScreenType.valueOf((String)json.get("screen_type"));
        this.homeCinema = (boolean)json.get("has_home_cinema");
    }
}
