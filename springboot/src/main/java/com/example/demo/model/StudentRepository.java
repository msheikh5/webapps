package com.example.demo.model;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class StudentRepository {
    Connection connection;
    Statement statement;

    StudentRepository(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection= DriverManager.getConnection("jdbc:mysql://host.docker.internal:3306/school","root","Mohammad2001");
            this.statement=connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public boolean login(int id, String password) throws SQLException {
        ResultSet resultSet=statement.executeQuery("select password from student where id="+id);
        if (resultSet.next())
            return resultSet.getString(1).equals(password);
        else
            return false;
    }

    public String showMarks(int id) throws SQLException {
        ResultSet resultSet=statement.executeQuery("select   course.courseName, marks.grade FROM marks  \n" +
                " JOIN course ON  course.courseId=marks.courseId where marks.id="+id);
        String message="";
        while (resultSet.next())
        {
            message+= "course: "+resultSet.getString(1)+" mark: "+resultSet.getInt(2)+"\n";
        }
        resultSet.close();
        return message;
    }


    public  String getCourseInformation(int id , int courseId) throws SQLException, ClassNotFoundException {
        String message="";
        ResultSet resultSet=statement.executeQuery("select marks.grade FROM marks where courseId="+courseId +" && id="+id);
        resultSet.next();
        message+="your grade: "+resultSet.getInt(1)+"\n";
        resultSet=statement.executeQuery("select count(grade) FROM marks where courseId="+courseId);
        resultSet.next();
        message+="number of student who took the exam: "+resultSet.getInt(1)+"\n";
        resultSet=statement.executeQuery("select max(grade) FROM marks where courseId="+courseId);
        resultSet.next();
        message+="max grade: "+resultSet.getInt(1)+"\n";
        resultSet=statement.executeQuery("select min(grade) FROM marks where courseId="+courseId);
        resultSet.next();
        message+="min grade: "+resultSet.getInt(1)+"\n";
        resultSet=statement.executeQuery("select avg(grade) FROM marks where courseId="+courseId);
        resultSet.next();
        message+="mean of grades: "+resultSet.getInt(1)+"\n";
        resultSet.close();
        return message;
    }

}
