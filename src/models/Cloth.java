package models;

import models.enums.Brand;
import models.enums.ClothMaterial;
import models.enums.ClothSize;
import models.enums.ProductType;
import org.json.simple.JSONObject;

public class Cloth extends Product{

    public  ClothMaterial clothMaterial;
    public ClothSize clothSize;

    public Cloth(long id, String name, long buy_price, ProductType type, Brand brand, String description,ClothMaterial clothMaterial,ClothSize clothSize) {
        super(id, name, buy_price, type, brand, description);
        this.clothMaterial = clothMaterial;
        this.clothSize = clothSize;
    }

    public Cloth(Cloth cloth){
        super(cloth);
        this.clothMaterial = cloth.clothMaterial;
        this.clothSize = cloth.clothSize;
    }

    public Cloth(Product product,ClothMaterial clothMaterial,ClothSize clothSize){
        super(product);
        this.clothMaterial = clothMaterial;
        this.clothSize = clothSize;
    }

    @Override
    public JSONObject toJSON(){
        JSONObject json = super.toJSON();
        json.put("cloth_material",this.clothMaterial.name());
        json.put("cloth_size",this.clothSize.name());
        return json;
    }

    public Cloth(JSONObject json){
        super(json);
        this.clothMaterial = ClothMaterial.valueOf((String)json.get("cloth_material"));
        this.clothSize = ClothSize.valueOf((String)json.get("cloth_size"));
    }
}
