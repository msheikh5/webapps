package com.example.demo3;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns ="/marks")
public class MarksServlet extends HttpServlet {
    StudentRepository studentRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository=new StudentRepository();
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        System.out.println(id);
        int choice= Integer.parseInt(req.getParameter("choice"));
        System.out.println(choice);
        if (choice==1)
        {
            try {
                String marks = studentRepository.showMarks(id);
                req.setAttribute("marks",marks);
                req.setAttribute("id",id);
                req.getRequestDispatcher("WEB-INF/welcome.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (choice==2)
        {
            req.setAttribute("id",id);
            req.getRequestDispatcher("WEB-INF/grades.jsp").forward(req,resp);

        }
        else if (choice==3)
        {
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
        }
        else
        {
            int courseId=Integer.parseInt(req.getParameter("courseId"));
            System.out.println(courseId);
            try {
                String statistics=studentRepository.getCourseInformation(id,courseId);
                req.setAttribute("id",id);
                req.setAttribute("statistics",statistics);
                req.getRequestDispatcher("WEB-INF/grades.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
