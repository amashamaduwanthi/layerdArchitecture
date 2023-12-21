package com.example.layeredarchitecture.controller;

import com.example.layeredarchitecture.Dao.custom.CustomerDAO;
import com.example.layeredarchitecture.Dao.custom.impl.CustomerDAOImp;
import com.example.layeredarchitecture.Dao.custom.impl.QueryDAOImp;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.SearchDto;
import com.example.layeredarchitecture.model.SearchOrderDTO;
import com.example.layeredarchitecture.view.tdm.SearchOrderTM;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchOrderFormController {
    public AnchorPane root;
    public TextField txtCustomerName;
    public JFXComboBox<String> cmbOrderId;
    public TableView<SearchOrderTM> tblOrderDetails;
    public Label lblId;
    public Label lblDate;
    public TextField txtOrderDate;
    public JFXComboBox<String> cmbCustomerId;
    CustomerDAO customerDAO = new CustomerDAOImp();

    QueryDAOImp queryDAO = new QueryDAOImp();

    public void initialize(){
        loadAllCustomerIDS();
    }

    private void loadAllCustomerIDS() {
        try {
            ArrayList<CustomerDTO> allCustomers = customerDAO.getAll();
            for (CustomerDTO c : allCustomers) {
                cmbCustomerId.getItems().add(c.getId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/com/example/layeredarchitecture/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void OrderIdOnAction(ActionEvent actionEvent) {
        String id = (String) cmbOrderId.getValue();
        try {

            tblOrderDetails.getItems().clear();
            ArrayList<SearchOrderDTO> dtolist = queryDAO.searchOrderDetail(id);

            for (SearchOrderDTO c : dtolist) {
                txtOrderDate.setText(c.getDate());
                tblOrderDetails.getItems().add(new SearchOrderTM(c.getItemcode(),c.getQty(),c.getUnitprice()));
            }
            tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
            tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("qty"));
            tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void cusIdOnAction(ActionEvent actionEvent) {
        String id = (String) cmbCustomerId.getValue();
        cmbOrderId.getItems().clear();
        try {
            ArrayList<SearchDto> dtolist = queryDAO.search(id);

            for (SearchDto c : dtolist) {
                cmbOrderId.getItems().add(c.getOid());
                txtCustomerName.setText(c.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void itemOnAction(ActionEvent actionEvent) {
    }
}
