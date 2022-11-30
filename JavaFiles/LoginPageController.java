package com.example.learn;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    TextField userEmail;

    @FXML
    PasswordField userPass;

    @FXML
    public void login(MouseEvent event) throws SQLException, IOException {
        String query = String.format("Select * from userDetails where userEmail='%s' AND userPass='%s'", userEmail.getText(),userPass.getText());
       ResultSet result =  HelloApplication.connection.executeQuery(query);



        if(result.next()){
       String userType = result.getString("userType");
            HelloApplication.emailId =result.getString("userEmail");
            if(userType.equals("buyer")){

           ProductPage products = new ProductPage();
           Header header = new Header();


           ListView<HBox> productList = products.showProducts();

           AnchorPane productPane = new AnchorPane();
           productPane.setLayoutX(150);
           productPane.setLayoutY(100);
           productPane.getChildren().add(productList);
           HelloApplication.root.getChildren().clear();
           HelloApplication.root.getChildren().addAll(header.root,productPane);

       } else{

           AnchorPane SellerPage = FXMLLoader.load((getClass().getResource("SellerPage.fxml")));
           HelloApplication.root.getChildren().add(SellerPage);
       }


       }
        else{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Product Add");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Login Failed! Please Try Again");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
        }
    }
}

