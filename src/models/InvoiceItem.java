package models;

import org.json.simple.JSONObject;

public class InvoiceItem {
    public Product product;
    public int quantity;
    public long price;

    public InvoiceItem(Product product,int quantity,long price){
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return product.name;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("product",product.toJSON());
        obj.put("quantity",quantity);
        obj.put("price",price);
        return obj;
    }

    public InvoiceItem(JSONObject obj){
        this.product = Product.fromJSON((JSONObject)obj.get("product"));
        this.quantity = Integer.valueOf(String.valueOf(obj.get("quantity")));
        this.price = (long)obj.get("price");
    }

    public long getTotalPrice(){
        return this.quantity*this.price;
    }
}
