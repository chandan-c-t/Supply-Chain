package com.example.learn;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;

public class CreateAccountController {

    @FXML
    TextField userName;
    @FXML
    TextField userEmail;
    @FXML
    TextField userPass;
    @FXML
    TextField userType;

    @FXML
    public void Create(MouseEvent event) throws SQLException, IOException{
        String query = String.format("Insert into userDetails values('%s','%s','%s','%s')",userEmail.getText(),userName.getText(),userPass.getText(),userType.getText());
        int response = HelloApplication.connection.executeUpdate(query);

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Create Account");
        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        if(response>0){

            dialog.setContentText("Account Created");

        }
        else{

            dialog.setContentText("Account is Not Created");

        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
