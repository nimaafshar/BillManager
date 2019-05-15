package models;

import models.enums.FitType;
import org.json.simple.JSONObject;

public class Pants extends Cloth{
    public FitType fitType;

    public Pants(Cloth cloth,FitType fitType) {
        super(cloth);
        this.fitType = fitType;
    }

    @Override
    public JSONObject toJSON(){
        JSONObject json = super.toJSON();
        json.put("fit_type",this.fitType.name());
        return json;
    }

    public Pants(JSONObject json){
        super(json);
        this.fitType = FitType.valueOf((String)json.get("fit_type"));
    }
}
