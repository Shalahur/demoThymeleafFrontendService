package com.example.demothymeleafFrontend.controller;

import com.example.demothymeleafFrontend.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class StudentController {
    public static final String PATH_VAR_ID = "{id}";

    public static final String BASE_ROUTE = "/student";
    public static final String INDEX_ROUTE = BASE_ROUTE + "/home";
    public static final String ADD_ROUTE = BASE_ROUTE + "/add";
    public static final String EDIT_ROUTE = BASE_ROUTE + "/edit/" + PATH_VAR_ID;
    public static final String DELETE_ROUTE = BASE_ROUTE + "/delete/" + PATH_VAR_ID;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(BASE_ROUTE)
    public String index(Model model) {
        ResponseEntity<List<Student>> studentResponse =
                restTemplate.exchange("http://localhost:8080/student",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                        });

        List<Student> students = studentResponse.getBody();
        model.addAttribute("students", students);
        return "student/index";
    }
}
