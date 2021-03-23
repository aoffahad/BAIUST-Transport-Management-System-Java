package messages;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SAMNOON
 */
public class MessageDBAdapter {
    public void setupDB() throws Exception{
       getConnection();
    }
    public static Connection getConnection() throws  Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String jdbcURL = "jdbc:mysql://localhost:3306/sadd";
            String username = "root";
            String password = "admin";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected");
            return conn;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public void addMessage(String title, String body, String sender, String type) throws Exception{
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("Insert into message(TITLE, BODY, SENDER, TYPE) values(?,?,?,?)");
        ps.setString(1, title);
        
        ps.setString(2, body);
        
        ps.setString(3, sender);
        
        ps.setString(4, type);
        ps.executeUpdate();
        con.close();   
    }
    
    public boolean hasMessage(String title) throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("select * from message where TITLE = ?");
        statement.setString(1, title);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return true;
        }
        return false;
    }
   
    
    public void removeMessage(String title) throws Exception{
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("Delete from message where TITLE = ?");
        ps.setString(1, title);
        ps.executeUpdate();
        con.close();
    }
    public ArrayList<MessageModelClass> getAllMessage() throws Exception{
        ArrayList <MessageModelClass> list = new ArrayList <> ();
        Connection con = getConnection();
        Statement statement = con.createStatement();
        String query = "Select * from message";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            MessageModelClass message = new MessageModelClass(rs.getString("TITLE"), rs.getString("BODY"), rs.getString("SENDER"), rs.getString("TYPE"));
            list.add(message);
        }
        
        return list;
    }
}
