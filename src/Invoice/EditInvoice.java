package Invoice;

import helper.InvoiceManager;
import helper.PdfHelper;
import helper.ProductManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Invoice;
import models.InvoiceItem;
import models.Product;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class EditInvoice implements Initializable {
    public TreeView<String> productsTree;
    public TextField quantityField;
    public Button addProductButton;
    public TextField priceField;
    public TableView<InvoiceItem> itemsTable;
    public TableColumn<String,InvoiceItem> nameColumn;
    public TableColumn<Integer,InvoiceItem> quantityColumn;
    public TableColumn<Long,InvoiceItem> priceColumn;
    public TableColumn<Long,InvoiceItem> totalpriceColumn;
    public TextField nameField;
    public DatePicker dateField;
    public Label idLabel;
    public Button deleteItemButton;
    public MenuItem saveButton;
    public MenuItem exportPdfButton;
    public MenuItem exportJsonButton;
    public Label statusLabel;
    public long invoiceId;
    public ProductManager productManager = new ProductManager();
    public static Invoice invoice;
    public Label totalCostLabel;
    Map<TreeItem<String>,Product> map = new LinkedHashMap<>();
    ObservableList<InvoiceItem> invoiceItems;
    private boolean somethingSaved = false;
    private InvoiceManager invoiceManager = new InvoiceManager();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set the id
        this.invoiceId = invoice.id;
        idLabel.setText("Invoice #"+this.invoiceId);

        //filling the tree
        TreeItem<String> root = new TreeItem<>(null);
        root.setExpanded(true);
        productsTree.setShowRoot(false);

        for(Product product: this.productManager.products){
            TreeItem<String> item = newItem(product.name,root);
            map.put(item,product);
        }
        productsTree.setRoot(root);
        quantityField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    quantityField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        priceField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    priceField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        productsTree.getSelectionModel().selectedItemProperty().addListener((v,oldItem,newItem)->{
            if(addProductButton.isDisable()){
                addProductButton.setDisable(false);
            }
            priceField.setText(String.valueOf(map.get(newItem).buy_price));
        });
        addProductButton.setDisable(true);

        invoiceItems = FXCollections.observableArrayList(invoice.items);
        itemsTable.setItems(invoiceItems);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        totalpriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        saveButton.setDisable(true);
        dateField.setValue(invoice.date);

        nameField.setText(invoice.costumerName);
        updateTotalCost();
    }

    public void deleteItem(ActionEvent actionEvent) {
        if(itemsTable.getSelectionModel().isEmpty()) return;

        InvoiceItem item = itemsTable.getSelectionModel().getSelectedItem();
        invoice.items.remove(item);
        invoiceItems.remove(item);
        if(invoiceItems.isEmpty()){
            saveButton.setDisable(true);
        }
        statusLabel.setText("item deleted");
        updateTotalCost();

    }

    public void save(ActionEvent actionEvent) {
        if(nameField.getText().trim().equals("")){
            statusLabel.setText("please fill some name");
        }else {
            invoice.costumerName = nameField.getText();
            invoice.date = dateField.getValue();
            invoiceManager.addInvoice(this.invoice);
            somethingSaved = true;
            Invoice.lastId++;
            System.out.println(invoice.toJSON());
            System.out.println("saved");
        }
    }

    public void exportPdf(ActionEvent actionEvent) {
        if(somethingSaved){
            String filename = "invoices/invoice_"+this.invoice.id+".pdf";
            new PdfHelper(this.invoice,filename).printInvoice();
            statusLabel.setText("exported as "+filename);
        }else {
            statusLabel.setText("save invoice first");
        }
    }

    public void exportJson(ActionEvent actionEvent) {
        if(this.somethingSaved){
            try{
                String filename = "invoices/invoice_"+this.invoice.id+".json";
                FileWriter file = new FileWriter(filename);
                JSONObject main = this.invoice.toJSON();
                main.put("total_cost",this.invoice.getTotalCost());
                file.write(main.toJSONString());
                file.close();
                //todo:fix the instant save problem
                statusLabel.setText("exported as "+filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            statusLabel.setText("save invoice first");
        }
    }

    static TreeItem<String> newItem(String text,TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(text);
        parent.getChildren().add(item);
        return item;
    }

    public void addProduct(ActionEvent actionEvent) {
        ArrayList<String> errors = new ArrayList<>();
        if(productsTree.getSelectionModel().isEmpty()){
            errors.add("please select something");
        }
        if(quantityField.getText().trim().equals("")){
            errors.add("must have quantity");
        }
        if(priceField.getText().trim().equals("")){
            errors.add("must have price");
        }

        if(errors.size() == 0){
            Product product = map.get(productsTree.getSelectionModel().getSelectedItem());
            int quantity = Integer.valueOf(quantityField.getText());
            long price = Long.valueOf(priceField.getText());
            InvoiceItem invoiceItem = new InvoiceItem(product,quantity,price);
            invoice.items.add(invoiceItem);
            invoiceItems.add(invoiceItem);
            statusLabel.setText("item added");
            updateTotalCost();
            saveButton.setDisable(false);
            System.out.println(invoiceItem.toJSON().toJSONString());
        }else {

            String error = "";
            Iterator<String> it = errors.iterator();
            if (it.hasNext()) error = it.next();
            while (it.hasNext()) {
                error += "|" + it.next();
            }
            statusLabel.setText(error);
        }
    }

    private void updateTotalCost(){
        totalCostLabel.setText(String.valueOf(invoice.getTotalCost()));
    }
}
