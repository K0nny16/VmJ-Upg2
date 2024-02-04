package Servlets;
import Model.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/addStudents")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Add a student</title></head>");

        out.println("<body>");
        out.println("<nav>");
        out.println("<a href= http://localhost:9090/addStudents> Add a student</a>");
        out.println("<a href= http://localhost:9090/addCourses> Add a course</a>");
        out.println("<a href= http://localhost:9090/Students> Students</a>");
        out.println("<a href= http://localhost:9090/Attendance> Sign up for courses</a>");
        out.println("</nav>");

        out.println("<h2>Add student!</h2>");
        out.println("<form method=post action=/addStudents>");
        out.println("First Name: <input type=text name=fname><br>");
        out.println("Last Name: <input type=text name=lname><br>");
        out.println("Town: <input type=text name =town><br>");
        out.println("Hobby: <input type=text name =hobby><br>");
        out.println("<input type=submit value=Add>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String town = req.getParameter("town");
        String hobby = req.getParameter("hobby");
        PrintWriter out = resp.getWriter();
        boolean response = Database.addStudent(fname,lname,town,hobby);
        if(!response){
            out.println("<html>");
            out.println("<head><title>Error!</title></head>");
            out.println("<body>");
            out.println("<h1> An error occurred!</h1>");
            out.println("<p> Please check the First name and Last name field");
        }
        else{
            out.println("<html>");
            out.println("<head><title> User Added! </title></head>");
            out.println("<h1> User added! </h1>");
            out.println("</html>");
        }
    }
}
//Keep
