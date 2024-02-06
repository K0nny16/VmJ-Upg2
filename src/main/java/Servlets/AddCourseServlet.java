package Servlets;
import Model.Database;
import Model.Status;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(urlPatterns = "/addCourses")
public class AddCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Add a Course!</title>");
        out.println("<link rel=stylesheet type=text/css href=/CSS/addStudents.css>");
        out.println("</head>");
        out.println("<body>");
        out.println("<nav>");
        out.println("<a href= http://localhost:9090/addStudents> Add a student</a>");
        out.println("<a href= http://localhost:9090/addCourses> Add a course</a>");
        out.println("<a href= http://localhost:9090/Students> Students</a>");
        out.println("<a href= http://localhost:9090/Attendance> Sign up for courses</a>");
        out.println("</nav>");
        out.println("<h2>Add a Course!</h2>");
        out.println("<form class=form-container method=post action=/addCourses>");
        out.println("Course name: <input type=text name=name><br>");
        out.println("YHP: <input type=text name=YHP><br>");
        out.println("Description: <input type=text name=description><br>");
        out.println("Lector: <input type=text name=lector><br>");
        out.println("<input type=submit value=Add>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String YHPString = req.getParameter("YHP");
        int YHP;
        PrintWriter out = resp.getWriter();
        try {
            YHP = Integer.parseInt(YHPString);
        } catch (NumberFormatException ex) {
            Status.error(out,"Please make sure you enter a number in the YHP field!");
            return;
        }
        String description = req.getParameter("description");
        String lector = req.getParameter("lector");
        boolean response = Database.addCourses(name, YHP, description, lector);
        if (response) {
            Status.success(out,"Course Added");
        }else
            Status.error(out,"Error");
    }
}
