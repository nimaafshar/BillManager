package models;

import models.enums.Brand;
import models.enums.ProductType;

public class Product {
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
}
