package schedule;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SAMNOON
 */
public class ScheduleDBAdapter {
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
    
    public void addSchedule(Time time, String from, String to, String busno) throws Exception{
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("Insert into schedule(TIME, FROM, TO, BUSNO) values(?, ?, ?, ?)");
        ps.setTime(1, time);
        
        ps.setString(2, from);
        
        ps.setString(3, to);
        
        ps.setString(4, busno);
        ps.executeUpdate();
        con.close();   
    }
    
    public boolean hasSchedule(Time time) throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("select * from schedule where TIME = ?");
        statement.setTime(1, (Time) time);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return true;
        }
        return false;
    }
   
    public void updateSchedule(Time time, String from, String to, String busno) throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("Update schedule set FROM = ?, TO = ?, BUSNO = ? where TIME = ?");
        statement.setString(1, from);
        statement.setString(2, to);
        statement.setString(3, busno);
        statement.setTime(4, (Time) time);
        statement.executeQuery();
        statement.close();
        con.close();
        
    }
    
    public void removeSchedule(Date time) throws Exception{
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("Delete from schedule where TIME = ?");
        ps.setDate(1, (java.sql.Date) time);
        ps.executeUpdate();
        con.close();
    }
    public ArrayList<ScheduleModelClass> getAllSchedule() throws Exception{
        ArrayList <ScheduleModelClass> list = new ArrayList <> ();
        Connection con = getConnection();
        Statement statement = con.createStatement();
        String query = "Select * from schedule";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            ScheduleModelClass schedule = new ScheduleModelClass(rs.getTime("TIME"), rs.getString("FROM"), rs.getString("TO"), rs.getString("BUSNO"));
            list.add(schedule);
        }
        
        return list;
    }
}
