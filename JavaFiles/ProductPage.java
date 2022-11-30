package com.example.learn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductPage {
    ListView<HBox> products;
    ListView<HBox> showProductsByName(String search) throws SQLException {
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from productDetails");
        products = new ListView<>();

        Label Name = new Label();
        Label Price = new Label();
        Label ProductId = new Label();


        HBox Details = new HBox();

        Name.setMinWidth(50);
        Price.setMinWidth(50);
        ProductId.setMinWidth(50);


        Name.setText(" Name ");
        Price.setText(" Price ");
        ProductId.setText(" ProductId ");

        Details.getChildren().addAll(ProductId,Name,Price);
        productList.add(Details);

        while(res.next()){
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())) {


                Label productName = new Label();
                Label productPrice = new Label();
                Label productId = new Label();
                Button buy = new Button();
                HBox productDetails = new HBox();
                productName.setMinWidth(50);
                productPrice.setMinWidth(50);
                productId.setMinWidth(50);
                buy.setText("Buy");

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailId.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.setContentText("Login First before placing Order..!");
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.showAndWait();
                        } else {

                            try {
                                Order place = new Order();
                                place.placeOrder(productId.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });

                productName.setText(res.getString("productName"));
                productPrice.setText(res.getString("price"));
                productId.setText("" + res.getInt("productId"));
                productDetails.getChildren().addAll(productId, productName, productPrice, buy);

                productList.add(productDetails);
            }

        }
        if(productList.size()==1){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Search Result");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("No Product Is Available for Your Search");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
        products.setItems(productList);
        return products;
    }


    ListView<HBox> showProducts() throws SQLException {
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from productDetails");
        products = new ListView<>();

        Label Name = new Label();
        Label Price = new Label();
        Label ProductId = new Label();


        HBox Details = new HBox();

       Name.setMinWidth(50);
        Price.setMinWidth(50);
        ProductId.setMinWidth(50);


        Name.setText(" Name ");
        Price.setText(" Price ");
        ProductId.setText(" ProductId ");

        Details.getChildren().addAll(ProductId,Name,Price);
        productList.add(Details);

        while(res.next()){
            Label productName = new Label();
            Label productPrice = new Label();
            Label productId = new Label();
            Button buy = new Button();
            HBox productDetails = new HBox();
            productName.setMinWidth(50);
            productPrice.setMinWidth(50);
            productId.setMinWidth(50);
            buy.setText("Buy");

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
            if(HelloApplication.emailId.equals("")){
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Login");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.setContentText("Login First before placing Order..!");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();
            } else{

                try {
                    Order place = new Order();
                    place.placeOrder(productId.getText());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
                }
            });

            productName.setText(res.getString("productName"));
            productPrice.setText(res.getString("price"));
            productId.setText(""+ res.getInt("productId"));
            productDetails.getChildren().addAll(productId,productName,productPrice,buy);

            productList.add(productDetails);

        }
        products.setItems(productList);
        return products;
    }
}
