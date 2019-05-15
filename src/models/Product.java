package models;

import models.enums.Brand;
import models.enums.ProductType;
import org.json.simple.JSONObject;

public class Product {
    public static long last_id=0;
    public long id;
    public String name;
    public long buy_price;
    public ProductType type;
    public Brand brand;
    public String description;

    public Product(long id, String name, long buy_price, ProductType type, Brand brand, String description) {
        this.id = id;
        this.name = name;
        this.buy_price = buy_price;
        this.type = type;
        this.brand = brand;
        this.description = description;
    }
    public Product(Product product){
        this.id = product.id;
        this.name = product.name;
        this.buy_price = product.buy_price;
        this.type = product.type;
        this.brand = product.brand;
        this.description = product.description;
    }


    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("id",this.id);
        json.put("name",this.name);
        json.put("buy_price",this.buy_price);
        json.put("type",this.type.name());
        json.put("brand",this.brand.name());
        json.put("description",this.description);
        return json;
    }

    public Product(JSONObject json){
        this.id = (long)json.get("id");
        this.name = (String)json.get("name");
        this.buy_price = (long)json.get("buy_price");
        this.type = ProductType.valueOf((String)json.get("type"));
        this.brand = Brand.valueOf((String)json.get("brand"));
        this.description = (String)json.get("description");
    }

    public void print(){
        System.out.println(this.id);
        System.out.println(this.name);
        System.out.println(this.buy_price);
        System.out.println(this.type.name());
        System.out.println(this.brand.name());
        System.out.println(this.description);
    }

    public static Product fromJSON(JSONObject json){
        ProductType type = ProductType.valueOf((String)json.get("type"));
        switch (type){
            case PRODUCT:
                return new Product(json);
            case CLOTH:
                return new Cloth(json);
            case SHIRT:
                return new Shirt(json);
            case PANTS:
                return new Pants(json);
            case ELECTRICAL_PRODUCT:
                return new ElectricalProduct(json);
            case TV:
                return new TV(json);
            case MOBILE:
                return new Mobile(json);
            case LAPTOP:
                return new Laptop(json);
            case BOOK:
                return new Book(json);
                default:
                    return null;
        }
    }
}
