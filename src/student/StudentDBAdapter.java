package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDBAdapter {
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
    public void addStudent(int ID, String NAME, String DEPT, String LEVEL, String TERM, String TYPE, int BUSFEE, String PASS) throws Exception{
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("Insert into student(ID, Name, DEPT, LEVEL, TERM, TYPE, BUSFEE, PASS) values(?,?,?,?,?,?,?,?)");
        ps.setInt(1, ID);
        ps.setString(2, NAME);
        
        ps.setString(3, DEPT);
        
        ps.setString(4, LEVEL);
        
        ps.setString(5, TERM);
        
        ps.setString(6, TYPE);
       
        ps.setInt(7, BUSFEE);
        ps.setString(8, PASS);
        
        ps.executeUpdate();
        con.close();
        
    }
    public boolean hasStudent(int id) throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("select * from student where id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return true;
        }
        return false;
    }
     public StudentModelClass getStudent(int id) throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("select * from student where id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
          StudentModelClass student = new StudentModelClass(rs.getInt("ID"), rs.getString("NAME"), rs.getString("DEPT"), rs.getString("LEVEL"), rs.getString("TERM"), rs.getString("TYPE"), rs.getInt("BUSFEE"), rs.getString("PASS"));
          return student;
        }
        return null;
    }
     public boolean verifyStudent(int id, String pass) throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("select * from student where id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if(rs.next()){
          StudentModelClass student = new StudentModelClass(rs.getInt("ID"), rs.getString("NAME"), rs.getString("DEPT"), rs.getString("LEVEL"), rs.getString("TERM"), rs.getString("TYPE"), rs.getInt("BUSFEE"), rs.getString("PASS"));
          if(student.getPass().equals(pass))
              return true;
        }
        return false;
    }
    public void updateStudent(int id, String level, String term, String type) throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("update student set LEVEL = ?, TERM = ?, TYPE = ? where ID = ?");
        statement.setString(1, level);
        statement.setString(2, term);
        statement.setString(3, type);
        statement.setInt(4, id);
        statement.executeUpdate();
        statement.close();
        con.close();
        
    }
    public void updatePass(int id, String pass) throws Exception{
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("update student set PASS = ? where ID = ?");
        statement.setString(1, pass);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
        con.close();
        
    }
    public void removeStudent(int ID) throws Exception{
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("Delete from student where ID = ?");
        ps.setInt(1, ID);
        ps.executeUpdate();
        con.close();
    }
    public ArrayList<StudentModelClass> getAllStudents() throws Exception{
        ArrayList <StudentModelClass> list = new ArrayList <> ();
        Connection con = getConnection();
        Statement statement = con.createStatement();
        String query = "Select * from student";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()){
            StudentModelClass student = new StudentModelClass(rs.getInt("ID"), rs.getString("NAME"), rs.getString("DEPT"), rs.getString("LEVEL"), rs.getString("TERM"), rs.getString("TYPE"), rs.getInt("BUSFEE"));
            list.add(student);
        }
        
        return list;
    }

}
