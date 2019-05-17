package helper;

import models.Invoice;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class InvoiceManager {
    ArrayList<Invoice> invoices;
    public InvoiceManager(){
        this.invoices = new ArrayList<>();
        this.readInvoices();
    }
    private void readInvoices(){
        invoices.clear();
        try {
            Reader reader = new FileReader("all_invoices.json");
            JSONObject main = (JSONObject)new JSONParser().parse(reader);
            Invoice.lastId = (long)main.get("last_id");
            JSONArray array = (JSONArray)main.get("invoices");
            if(array != null) {
                Iterator<JSONObject> it = array.iterator();
                while (it.hasNext()) {
                    JSONObject item = it.next();
                    this.invoices.add(new Invoice(item));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("all_invoices.json not found");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeInvoices(){
        long lastId = 0;
        JSONObject main = new JSONObject();
        JSONArray array = new JSONArray();
        for(Invoice invoice:this.invoices){
            array.add(invoice.toJSON());
            if(invoice.id > lastId){
                lastId = invoice.id;
            }
        }
        main.put("last_id",lastId);
        main.put("invoices",array);
        try{
            FileWriter file = new FileWriter("all_invoices.json");
            file.write(main.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addInvoice(Invoice invoice){
        this.invoices.add(invoice);
        this.writeInvoices();
    }
}
