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

@WebServlet(urlPatterns ="/login")
public class LoginServlet extends HttpServlet {
    StudentRepository studentRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository=new StudentRepository();
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        String password= req.getParameter("password");
        try {
            if (studentRepository.login(id,password)) {
                req.setAttribute("id",id);
                req.getRequestDispatcher("WEB-INF/welcome.jsp").forward(req, resp);
                System.out.println("yes");
            }
            else{
                req.setAttribute("errorMessage","Wrong username or password!" );
                req.getRequestDispatcher("WEB-INF/login.jsp").forward(req,resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
