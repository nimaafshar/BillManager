package statistics;

import helper.InvoiceManager;
import helper.ProductManager;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import models.Invoice;
import models.InvoiceItem;
import models.Product;

import javax.swing.text.html.HTMLDocument;
import java.net.URL;
import java.util.*;

public class Statistics implements Initializable {
    public Label totalProfitLabel;
    public ComboBox<String> productCombo;
    public Label productProfitLabel;
    public ComboBox<String> bestsellersCombo;
    public InvoiceManager invoiceManager;
    Map<Long,Long> profitMap;
    Map<Long,Integer> saleMap;
    Map<String,Long> nameMap;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        invoiceManager = new InvoiceManager();
        //calculate totalprofit
        long totalProfit = 0;
        for(Invoice invoice:invoiceManager.invoices){
            totalProfit+=invoice.getProfit();
        }
        totalProfitLabel.setText(String.valueOf(totalProfit));

        //profit & sale for each product
        profitMap = new HashMap<>();
        saleMap = new HashMap<>();
        ArrayList<Long> salesKeys = new ArrayList<>();
        for(Invoice invoice:invoiceManager.invoices){
            for(InvoiceItem item:invoice.items){
                long last = 0;
                if(profitMap.containsKey(item.product.id)) {
                    last = profitMap.get(item.product.id);
                }
                profitMap.put(item.product.id,last+(item.price-item.product.buy_price)*item.quantity);

                int sale = 0;
                if(saleMap.containsKey(item.product.id)){
                    sale = saleMap.get(item.product.id);

                }else {
                    salesKeys.add(item.product.id);
                }
                saleMap.put(item.product.id,sale+1);
            }
        }

        nameMap = new HashMap<>();
        ProductManager productManager = new ProductManager();
        productManager.arrangeById();
        for(Long id:profitMap.keySet()){
            nameMap.put(productManager.getById(id).name,id);
        };
        productCombo.getItems().addAll(nameMap.keySet());
        productCombo.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->{
            productProfitLabel.setText(String.valueOf(profitMap.get(nameMap.get(newValue))));
        });
        System.out.println(saleMap);
        salesKeys.sort(Comparator.comparingInt(o -> saleMap.get(o)).reversed());
        System.out.println(salesKeys);
        Iterator<Long> it = salesKeys.iterator();
        int i=0;
        while (i<10&&it.hasNext()){
            bestsellersCombo.getItems().add(productManager.getById(it.next()).name);
        }
    }
}
