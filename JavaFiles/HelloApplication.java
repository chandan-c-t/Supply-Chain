package com.example.learn;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HelloApplication extends Application {
        public static Group root;
        public static Connection connection;

        public static String emailId;

    @Override
    public void start(Stage stage) throws Exception {
        emailId="";

        connection = new Connection();

    root = new Group();
 Header header = new Header();
 ProductPage products = new ProductPage();
        ListView<HBox> productList = products.showProducts();

        AnchorPane productPane = new AnchorPane();
        productPane.setLayoutX(50);
        productPane.setLayoutY(100);
        productPane.getChildren().add(productList);
       root.getChildren().addAll(header.root,productPane);

        stage.setTitle("Suppy Chain");

        stage.setScene(new Scene(root, 500, 500));
        stage.show();
        stage.setOnCloseRequest(e ->{
            try {

                connection.con.close();
                System.out.println("connection closed");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}