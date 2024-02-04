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
@WebServlet(urlPatterns = "/Attendance")
public class AttendanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head><title>Sign up for a course!</title></head>");
        out.println("<body>");
        out.println("<nav>");
        out.println("<a href= http://localhost:9090/addStudents> Add a student</a>");
        out.println("<a href= http://localhost:9090/addCourses> Add a course</a>");
        out.println("<a href= http://localhost:9090/Students> Students</a>");
        out.println("<a href= http://localhost:9090/Attendance> Sign up for courses</a>");
        out.println("</nav>");
        out.println("<div>");
        out.println("<h2> Courses List </h2>");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Name</th><th>YHP</th><th>Description</th><th>Lector</th><th>Course ID</th></tr>");
        ArrayList<String> courses = Database.courses();
        for(String coursesInfo : courses){
            String[] fields = coursesInfo.split(",");
            String lector = (fields.length > 3 && !fields[3].isEmpty()) ? fields[3] : "";
            out.println("<tr><td>"+fields[0]+"</td><td>"+fields[1]+"</td><td>"+fields[2]+"</td><td>"+lector+"</td><td>"+fields[4]+"</tr>");
        }
        out.println("</table>");
        out.println("</div>");
        out.println("<div>");
        out.println("<h2> Students List </h2>");
        out.println("<table border=\"1\">");
        out.println("<tr><th>Name</th><th>Town</th><th>Hobby</th><th>Student ID</th></tr>");
        ArrayList<String> students = Database.students();
        for(String studentInfo : students){
            String[] fields = studentInfo.split(",");
            String town = (fields.length > 2 && !fields[2].isEmpty()) ? fields[2] : "";
            String hobby = (fields.length > 3 && !fields[3].isEmpty()) ? fields[3] : "";
            out.println("<tr><td>"+fields[0]+" "+fields[1]+"</td><td>"+town+"</td><td>"+hobby+"</td><td>"+fields[4]+"</td></tr>");
        }
        out.println("</table>");
        out.println("</div>");
        out.println("<div>");
        out.println("<form method=post action=/Attendance>");
        out.println("Course ID: <input type=text name=courseID><br>");
        out.println("Student ID: <input type=text name=studentID><br>");
        out.println("<input type=submit value=Add>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentIDText = req.getParameter("studentID");
        String courseIDText = req.getParameter("courseID");
        int studentsID,courseID;
        PrintWriter out = resp.getWriter();
        try{
            studentsID = Integer.parseInt(studentIDText);
            courseID = Integer.parseInt(courseIDText);
        }catch(Exception ex){
            out.println("<html>");
            out.println("<head><title> Error! </title></head>");
            out.println("<h1> You have to enter the course ID and the Student ID as numbers! </h1>");
            out.println("<a href=http://localhost:9090/Attendance> Back </a> ");
            out.println("</html>");
            return;
        }
        boolean response = Database.enroll(courseID,studentsID);
        if (!response) {
            out.println("<html><head><title>Error!</title></head>");
            out.println("<body><h1>An error occurred!</h1><p>All fields must be filled in!</p></body></html>");
            out.println("<a href=http://localhost:9090/Attendance> Back </a> ");
        } else {
            out.println("<html><head><title>Course add to student!</title></head>");
            out.println("<a href=http://localhost:9090/Attendance> Back </a> ");
            out.println("<body><h1>Course add to student!</h1></body></html>");
        }
    }
}
