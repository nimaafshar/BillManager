package models;

import javafx.scene.control.DatePicker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Invoice {
    public static long lastId = 0;
    public ArrayList<InvoiceItem> items;
    public String costumerName;
    public LocalDate date;
    public long id;

    public Invoice(){
        items = new ArrayList<>();
        costumerName = "";
        date = LocalDate.now();
    }

    public long getTotalCost(){
        long totalCost = 0;
        for(InvoiceItem item:this.items){
            totalCost +=item.getTotalPrice();
        }
        return totalCost;
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("id",this.id);
        obj.put("costumer_name",this.costumerName);
        obj.put("date",date.toString());
        JSONArray arr = new JSONArray();
        for(InvoiceItem item:items){
            arr.add(item.toJSON());
        }
        obj.put("items",arr);
        return obj;
    }

    public Invoice(JSONObject obj){
        this.id = (long) obj.get("id");
        this.costumerName = (String) obj.get("costumer_name");
        this.date = LocalDate.parse((String)obj.get("date"));
        this.items = new ArrayList<>();
        JSONArray arr = (JSONArray)obj.get("items");
        Iterator it = arr.iterator();
        while (it.hasNext()){
            items.add(new InvoiceItem((JSONObject)it.next()));
        }
    }

    public long getProfit(){
        long profit = 0;
        for(InvoiceItem item:this.items){
            profit+=(item.price-item.product.buy_price)*item.quantity;
        }
        return profit;
    }


}
