package com.example.demo.Controller;

import com.example.demo.model.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class MarksController {
    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/marks")
    public String showWelcome(ModelMap modelMap, @RequestParam int id, @RequestParam int choice,@RequestParam int courseId) throws SQLException, ClassNotFoundException {
        if (choice == 1)
        {
            String marks=studentRepository.showMarks(id);
            modelMap.put("id",id);
            modelMap.put("marks",marks);
            return "welcome";
        }
        else if (choice==2)
        {
            modelMap.put("id",id);
            return "grades";
        }
        else if (choice==3)
        {
            return "login";
        }
        else
        {
            String statistics=studentRepository.getCourseInformation(id,courseId);
            modelMap.put("id",id);
            modelMap.put("statistics",statistics);
            return "grades";
        }



    }
}
