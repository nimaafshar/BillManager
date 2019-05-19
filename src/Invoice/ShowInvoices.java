package Invoice;

import helper.InvoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Invoice;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ShowInvoices implements Initializable {
    public TableView<Invoice> invoicesTable;
    public TableColumn<Invoice,Long> idColumn;
    public TableColumn<Invoice,String> costumerColumn;
    public TableColumn<Invoice, String> dateColumn;
    public TableColumn<Invoice,Long> costColumn;
    public TableColumn<Invoice,Long> profitColumn;
    public Button editButton;
    public Button deleteButton;
    public InvoiceManager invoiceManager = new InvoiceManager();
    ObservableList<Invoice> observables;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        costumerColumn.setCellValueFactory(new PropertyValueFactory<>("costumerName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));

        observables = FXCollections.observableArrayList(invoiceManager.invoices);
        invoicesTable.setItems(observables);

        editButton.setDisable(true);
        deleteButton.setDisable(true);
        invoicesTable.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue)->{
                if(!invoicesTable.getSelectionModel().isEmpty()){
                    editButton.setDisable(false);
                    deleteButton.setDisable(false);
                }else{
                    editButton.setDisable(true);
                    deleteButton.setDisable(true);
                }
        });
    }

    public void edit(ActionEvent actionEvent) throws IOException {
        EditInvoice.invoice = invoicesTable.getSelectionModel().getSelectedItem();
        this.delete(actionEvent);
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../Invoice/edit_invoice.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.showAndWait();
        invoiceManager.readInvoices();
        observables = FXCollections.observableArrayList(invoiceManager.invoices);
        invoicesTable.setItems(observables);
        System.out.println("code runned");
        editButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    public void delete(ActionEvent actionEvent) {
        this.invoiceManager.removeInvoice(invoicesTable.getSelectionModel().getSelectedItem());
        observables.remove(invoicesTable.getSelectionModel().getSelectedItem());
        invoicesTable.getSelectionModel().clearSelection();
        editButton.setDisable(true);
        deleteButton.setDisable(true);
    }
}
