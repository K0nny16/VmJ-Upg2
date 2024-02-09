package Servlets;
import Model.Database;
import Model.Status;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
@WebServlet(urlPatterns = "/Students")
public class StudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Student List</title>");
        out.println("<link rel=stylesheet type=text/css href=/CSS/student.css>");
        out.println("</head>");
        out.println("<body>");
        out.println("<nav>");
        out.println("<a href= http://localhost:9090/addStudents> Add a student</a>");
        out.println("<a href= http://localhost:9090/addCourses> Add a course</a>");
        out.println("<a href= http://localhost:9090/Students> Students</a>");
        out.println("<a href= http://localhost:9090/Attendance> Sign up for courses</a>");
        out.println("</nav>");
        out.println("<h2>Search for a Student</h2>");
        out.println("<form class=form-container method=post action=/Students>");
        out.println("First Name: <input type=text name=fname><br>");
        out.println("Last Name: <input type=text name=lname><br>");
        out.println("<input type=submit value=Search>");
        out.println("</form>");
        out.println("<table class=table-container border=1>");
        out.println("<tr><th>Name</th><th>Town</th><th>Hobby</th></tr>");
        ArrayList<String> students = Database.students();
        for(String studentInfo : students){
            String[] fields = studentInfo.split(",");
            String town = (fields.length > 2 && !fields[2].isEmpty()) ? fields[2] : "";
            String hobby = (fields.length > 3 && !fields[3].isEmpty()) ? fields[3] : "";
            out.println("<tr><td>"+fields[0]+" "+fields[1]+"</td><td>"+town+"</td><td>"+hobby+"</td></tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        PrintWriter out = resp.getWriter();
        ArrayList<String> studentCourses = Database.studentCourses(fname,lname);
        if(!studentCourses.isEmpty()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student List</title>");
            out.println("<link rel=stylesheet type=text/css href=/CSS/student.css>");
            out.println("</head>");
            out.println("<body>");
            out.println("<nav>");
            out.println("<a href= http://localhost:9090/addStudents> Add a student</a>");
            out.println("<a href= http://localhost:9090/addCourses> Add a course</a>");
            out.println("<a href= http://localhost:9090/Students> Students</a>");
            out.println("<a href= http://localhost:9090/Attendance> Sign up for courses</a>");
            out.println("</nav>");
            out.println("<h2>Courses for "+fname+" "+lname+".</h2>");
            out.println("<br>");
            out.println("<table class=table-container border=1>");
            out.println("<tr><th>Name</th><th>Course</th><th>Course ID</th></tr>");
            for(String student : studentCourses){
                String[] fields = student.split(",");
                out.println("<tr><td>"+fields[0]+" "+fields[1]+"</td><td>"+fields[2]+"</td><td>"+fields[3]+"</td></tr>");
            }
            out.println("</table>");
            out.println("<a href=/Students><button class=backButton>Back</button></a>");
            out.println("</body>");
            out.println("</html>");
        }
        else
            Status.error(out,"Error the Firstname and Lastname field cant be empty or the student don't exist!");
    }
}
