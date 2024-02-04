package Servlets;
import Model.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
@WebServlet(urlPatterns = "/Students")
public class StudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Student List</title></head>");
        out.println("<body>");
        out.println("<nav>");
        out.println("<a href= http://localhost:9090/addStudents> Add a student</a>");
        out.println("<a href= http://localhost:9090/addCourses> Add a course</a>");
        out.println("<a href= http://localhost:9090/Students> Students</a>");
        out.println("<a href= http://localhost:9090/Attendance> Sign up for courses</a>");
        out.println("</nav>");
        out.println("<h2>Search for a Student</h2>");
        out.println("<form method=\"post\" action=\"/Students\">");
        out.println("First Name: <input type=\"text\" name=\"fname\"><br>");
        out.println("Last Name: <input type=\"text\" name=\"lname\"><br>");
        out.println("<input type=\"submit\" value=\"Search\">");
        out.println("</form>");
        out.println("<h2> Students List </h2>");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Name</th><th>Town</th><th>Hobby</th></tr>");
        ArrayList<String> students = Database.students();
        for(String studentInfo : students){
            String[] fields = studentInfo.split(",");
            String town = (fields.length > 2 && !fields[2].isEmpty()) ? fields[2] : "";
            String hobby = (fields.length > 3 && !fields[3].isEmpty()) ? fields[3] : "";
            out.println("<tr><td>"+fields[0]+" "+fields[1]+"</td><td>"+town+"</td><td>"+hobby+"</td></tr>");
        }
        out.println("</table>");
        out.println("<br>");
        out.println("<a href= http://localhost:9090/Attendance> Attendance</a>");
        out.println("<a href= http://localhost:9090/Courses> Courses</a>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");

        ArrayList<String> studentCourses = Database.studentCourses(fname,lname);
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Student Courses</title></head>");
        out.println("<body>");
        if (studentCourses.isEmpty()) {
            out.println("<h2>No courses found for student " + fname + " " + lname + "</h2>");
            out.println("<br>");
            out.println("<a href=\"/Students\">Back to Student List</a>");
        } else {
            out.println("<h2> Student Courses for " + fname + " " + lname + "</h2>");
            out.println("<table border=\"1\">");
            out.println("<tr><th>Student</th><th>Course</th><th>Course ID</th></tr>");
            for (String courseInfo : studentCourses) {
                String[] fields = courseInfo.split(",");
                out.println("<tr><td>" + fields[0]+" "+fields[1]+ "</td><td>" + fields[2] + "</td><td>"+fields[3]+"</td></tr>");
            }
            out.println("</table>");
            out.println("<br>");
            out.println("<a href=\"/Students\">Back to Student List</a>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
//keep
