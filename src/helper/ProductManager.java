package helper;

import models.Product;

import java.util.ArrayList;

public class ProductManager {
    public static ArrayList<Product> products;

    ProductManager(){
        //todo:read products from file here
    }

    public static void addProduct(Product product){
        products.add(product);
        Product.last_id = product.id;
    }


}
