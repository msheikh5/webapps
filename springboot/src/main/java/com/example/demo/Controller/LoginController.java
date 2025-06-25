package com.example.demo.Controller;

import com.example.demo.model.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class LoginController {
    StudentRepository studentRepository;
    LoginController(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    @GetMapping("/login")
    public String login(){
       return "login";
    }
    @PostMapping("/login")
    public String checkLogin(ModelMap modelMap, @RequestParam int id, @RequestParam String password) throws SQLException {
if (studentRepository.login(id,password)) {
    modelMap.put("id",id);
    return "welcome";
}
    else
{
    modelMap.put("errorMessage","Wrong username or password!");
    return "login";
}

    }
}
