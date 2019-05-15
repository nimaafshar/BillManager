package models;

import models.enums.MobileOS;
import org.json.simple.JSONObject;

public class Mobile extends ElectricalProduct {
    int storage;//in gigabytes
    MobileOS mobileOS;
    public Mobile(ElectricalProduct electricalProduct,MobileOS mobileOS,int storage) {
        super(electricalProduct);
        this.mobileOS = mobileOS;
        this.storage =storage;
    }

    @Override
    public JSONObject toJSON(){
        JSONObject json = super.toJSON();
        json.put("os",this.mobileOS.name());
        json.put("storage",this.storage);
        return json;
    }

    public Mobile(JSONObject json){
        super(json);
        this.storage = (int)json.get("storage");
        this.mobileOS = MobileOS.valueOf((String)json.get("os"));
    }
}
