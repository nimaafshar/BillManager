package helper;

import models.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProductManager {
    public ArrayList<Product> products;

    private Map<Long,Product> arranged;
    public ProductManager() {
        products = new ArrayList<>();
        this.readProducts();
    }

    public void arrangeById(){
        this.arranged = new HashMap<>();
        for(Product p :this.products){
            arranged.put(p.id,p);
        }
    }

    public Product getById(long id){
        return  this.arranged.get(id);
    }

    private void readProducts(){
        try {
            Reader reader = new FileReader("products.json");
            JSONObject main = (JSONObject)new JSONParser().parse(reader);
            Product.last_id = (long)main.get("last_id");
            JSONArray array = (JSONArray)main.get("products");
            if(array != null) {
                Iterator<JSONObject> it = array.iterator();
                while (it.hasNext()) {
                    JSONObject item = it.next();
                    this.products.add(Product.fromJSON(item));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("products.json not found");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product){
        products.add(product);
        Product.last_id = product.id;
        this.saveProducts();
    }

    private void saveProducts(){
        long lastId = 1;
        JSONObject main = new JSONObject();
        JSONArray array = new JSONArray();
        for(Product product:this.products){
            array.add(product.toJSON());
            if(product.id > lastId){
                lastId = product.id;
            }
        }
        main.put("last_id",lastId);
        main.put("products",array);
        try{
            FileWriter file = new FileWriter("products.json");
            file.write(main.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeProduct(Product pr){
        this.products.remove(pr);
        this.saveProducts();
    }

}
