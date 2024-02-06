package Servlets;
import Model.Database;
import Model.Status;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(urlPatterns = "/addStudents")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Add a student</title>");
        out.println("<link rel=stylesheet type=text/css href=/CSS/addStudents.css>");
        out.println("</head>");
        out.println("<body>");
        out.println("<nav>");
        out.println("<a href= http://localhost:9090/addStudents> Add a student</a>");
        out.println("<a href= http://localhost:9090/addCourses> Add a course</a>");
        out.println("<a href= http://localhost:9090/Students> Students</a>");
        out.println("<a href= http://localhost:9090/Attendance> Sign up for courses</a>");
        out.println("</nav>");
        out.println("<h2>Add Student</h2>");
        out.println("<form class=form-container method=post action=/addStudents>");
        out.println("First Name: <input type=text name=fname><br>");
        out.println("Last Name: <input type=text name=lname><br>");
        out.println("Town: <input type=text name=town><br>");
        out.println("Hobby: <input type=text name=hobby><br>");
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
        if(!fname.isEmpty()&&!lname.isEmpty()){
            boolean response = Database.addStudent(fname, lname, town, hobby);
            if (response)
                Status.success(out,"Student Added");
            else
                Status.error(out,"Error");
        }
        else
            Status.error(out,"Firstname and Lastname cant be empty");
    }
}
