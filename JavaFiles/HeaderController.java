package com.example.learn;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {

@FXML
Button loginButton;

@FXML
    Label email;
@FXML
    TextField searchText;
@FXML
Button LogoutButton;

@FXML
public void initialize(){
    if(!HelloApplication.emailId.equals("")){
        loginButton.setOpacity(0);
        email.setText(HelloApplication.emailId);
    }
}
@FXML
public void login(MouseEvent event) throws IOException {
    AnchorPane loginPage = FXMLLoader.load((getClass().getResource("LoginPage.fxml")));
    HelloApplication.root.getChildren().add(loginPage);
    }

    @FXML
    public void newUser(MouseEvent event) throws IOException {
        AnchorPane loginPage = FXMLLoader.load((getClass().getResource("CreateAccount.fxml")));
        HelloApplication.root.getChildren().add(loginPage);
    }

    @FXML
    public void Search(MouseEvent event) throws IOException, SQLException {

        Header header = new Header();

        ProductPage products = new ProductPage();
        AnchorPane ProPage = new AnchorPane();
        ProPage.getChildren().add(products.showProductsByName(searchText.getText()));
        ProPage.setLayoutX(150);
        ProPage.setLayoutY(100);

        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,ProPage);

    }
    @FXML
    public void Logout(MouseEvent event) throws IOException, SQLException {
        if(LogoutButton.getOpacity()==0) {
            LogoutButton.setOpacity(1);
            LogoutButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    HelloApplication.emailId="";
                    LogoutButton.setOpacity(0);
                    try {
                        Header header = new Header();
                        HelloApplication.root.getChildren().add(header.root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        } else {
            LogoutButton.setOpacity(0);
}

    }

}
