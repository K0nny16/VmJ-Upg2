package Model;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
public class Database {
    static Dotenv dotenv = Dotenv.load();
    private static String getConnection(String port,String user,String db,String pw){
        return "jdbc:mysql://localhost:"+port+"/"+db+"?user="+user+"&password="+pw;
    }
    public static ArrayList<String> students(){
        ArrayList<String> student = new ArrayList<>();
        try{
            String studentInfo;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(getConnection(dotenv.get("User_Port"),dotenv.get("User_User"),dotenv.get("User_DB"),dotenv.get("User_PW")));
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT *FROM Students");
            while(rs.next()){
                studentInfo = rs.getString("fname")+","+rs.getString("lname")+","+rs.getString("town")+","+rs.getString("hobby");
                student.add(studentInfo);
            }
            conn.close();
        }catch (Exception ex){
            ex.getStackTrace();
        }
        return student;
    }
    public static ArrayList<String> courses(){
        ArrayList<String> courses = new ArrayList<>();
        try{
            String coursesInfo;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(getConnection(dotenv.get("User_Port"),dotenv.get("User_User"),dotenv.get("User_DB"),dotenv.get("User_PW")));
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT *FROM Courses");
            while(rs.next()){
                coursesInfo = rs.getString("name")+","+rs.getInt("YHP")+","+rs.getString("description")+","+rs.getString("lector");
                courses.add(coursesInfo);
            }
            conn.close();
        }catch(Exception ex){
            ex.getStackTrace();
        }
        return courses;
    }
    public static ArrayList<String> studentCourses(String fname,String lname){
        ArrayList<String> studentCourses = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String studentCourseInfo;
            Connection conn = DriverManager.getConnection(getConnection(dotenv.get("User_Port"),dotenv.get("User_User"),dotenv.get("User_DB"),dotenv.get("User_PW")));
            String sql = "SELECT students.fname, students.lname,courses.name, courses.id FROM attendance JOIN students ON attendance.students_id = students.id JOIN courses ON attendance.courses_id = courses.id WHERE students.fname = ? AND students.lname = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,fname);
            ps.setString(2,lname);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                studentCourseInfo = rs.getString("fname")+","+rs.getString("lname")+","+rs.getString("name")+","+rs.getInt("id");
                studentCourses.add(studentCourseInfo);
            }
            conn.close();
        }catch(Exception ex){
            ex.getStackTrace();
        }
        return studentCourses;
    }
    public static boolean addStudent(String fname,String lname,String town,String hobby){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(getConnection(dotenv.get("User_Port"),dotenv.get("Admin_User"),dotenv.get("User_DB"),dotenv.get("Admin_PW")));
            String sql = "INSERT INTO students(fname, lname, town, hobby) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,town);
            ps.setString(4,hobby);
            int rowsChanged = ps.executeUpdate();
            conn.close();
            return rowsChanged > 0;
        }catch(Exception ex){
            System.out.println("Error "+ex.getMessage());
            return false;
        }
    }
    public static boolean addCourses(String name,int YHP,String description,String lector){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(getConnection(dotenv.get("User_Port"),dotenv.get("Admin_User"),dotenv.get("User_DB"),dotenv.get("Admin_PW")));
            String sql = "INSERT INTO courses(name,YHP,description,lector) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,YHP);
            ps.setString(3,description);
            ps.setString(4,lector);
            int rowsChanged = ps.executeUpdate();
            conn.close();
            return rowsChanged > 0;
        }catch(Exception ex){
            System.out.println("Error "+ex.getMessage());
            return false;
        }
    }
}