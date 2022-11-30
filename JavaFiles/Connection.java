package com.example.learn;

import java.sql.*;

public class Connection {
    String url = "jdbc:mysql://localhost:3306/project";
    String user = "root";
    String password = "Chandu@777";
   public java.sql.Connection con = null;

  public  Connection(){
        try{
            con = DriverManager.getConnection(url,user, password);
            if(con!=null){
                System.out.println("db connected..!");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String query){
        ResultSet res = null;
        try {


            Statement statement = con.createStatement();
            res = statement.executeQuery(query);
            return res;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public int executeUpdate(String query) {
        int res = 0;
        try {


            Statement statement = con.createStatement();
            res = statement.executeUpdate(query);
            return res;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
