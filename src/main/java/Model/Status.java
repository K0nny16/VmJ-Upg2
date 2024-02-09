package Model;
import java.io.PrintWriter;
import static Model.Database.getError;
import static Model.Database.setError;
public class Status {
    public static void error(PrintWriter out, String title){
        out.println("<html>");
        out.println("<head>");
        out.println("<link rel=stylesheet type=text/css href=/CSS/status.css>");
        out.println("<title>"+title+"!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<nav>");
        out.println("<a href= http://localhost:9090/addStudents> Add a student</a>");
        out.println("<a href= http://localhost:9090/addCourses> Add a course</a>");
        out.println("<a href= http://localhost:9090/Students> Students</a>");
        out.println("<a href= http://localhost:9090/Attendance> Sign up for courses</a>");
        out.println("</nav>");
        out.println("<h2>"+title+"</h2>");
        out.println("<p>"+getError()+"</p>");
        out.println("</body>");
        out.println("</html>");
        setError("");
    }
    public static void success(PrintWriter out,String title){
        out.println("<html>");
        out.println("<head><title> "+title+"! </title>");
        out.println("<link rel=stylesheet type=text/css href=/CSS/status.css></head>");
        out.println("<body>");
        out.println("<nav>");
        out.println("<a href= http://localhost:9090/addStudents> Add a student</a>");
        out.println("<a href= http://localhost:9090/addCourses> Add a course</a>");
        out.println("<a href= http://localhost:9090/Students> Students</a>");
        out.println("<a href= http://localhost:9090/Attendance> Sign up for courses</a>");
        out.println("</nav>");
        out.println("</body>");
        out.println("<h2> "+title+"! </h2>");
        out.println("</html>");
    }
}
