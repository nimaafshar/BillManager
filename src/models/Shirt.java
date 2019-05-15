package models;

import models.enums.Sleeve;
import org.json.simple.JSONObject;

public class Shirt extends Cloth{
    public Sleeve sleeve;

    public Shirt(Cloth cloth,Sleeve sleeve) {
        super(cloth);
        this.sleeve = sleeve;
    }
    @Override
    public JSONObject toJSON(){
        JSONObject json = super.toJSON();
        json.put("sleeve",this.sleeve.name());
        return json;
    }
    public Shirt(JSONObject json){
        super(json);
        this.sleeve = Sleeve.valueOf((String)json.get("sleeve"));
    }
}
