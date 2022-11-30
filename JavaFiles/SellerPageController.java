package com.example.learn;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {
    @FXML
    TextField productName;
    @FXML
    TextField price;
    @FXML
    TextField userEmail;

    @FXML
    public void Add(MouseEvent event) throws SQLException {
        ResultSet res = HelloApplication.connection.executeQuery("Select max(productId) from productDetails");
        int productIdN=0;
        if(res.next()){
            productIdN = res.getInt("max(productId)")+1;
        }
        String query = String.format("Insert into productDetails values(%s,'%s',%s,'%s')",productIdN,productName.getText(),price.getText(),userEmail.getText());
        int response = HelloApplication.connection.executeUpdate(query);

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Product Add");
        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

       if(response>0){

           dialog.setContentText("A new Product is Added");

       }
       else{

           dialog.setContentText("The Product is not Added");

       }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
