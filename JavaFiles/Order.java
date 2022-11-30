package com.example.learn;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {
    void placeOrder(String productId) throws SQLException {
        ResultSet res = HelloApplication.connection.executeQuery("Select max(orderId) from orders");
        int orderId = 0;
        if(res.next()){
            orderId = res.getInt("max(orderId)")+1;

            String query = String.format("Insert into orders values(%s,%s,'%s')",orderId,productId,HelloApplication.emailId);
            int response = HelloApplication.connection.executeUpdate(query);
            if(response>0){
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Order");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Order is Placed");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();

            }
        }


    }
}
