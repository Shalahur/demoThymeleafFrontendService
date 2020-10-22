package com.example.demothymeleafFrontend.controller;

import com.example.demothymeleafFrontend.dto.Student;
import com.example.demothymeleafFrontend.form.StudentForm;
import com.example.demothymeleafFrontend.support.APIBaseURI;
import com.example.demothymeleafFrontend.support.Constant;
import com.example.demothymeleafFrontend.support.WebLinkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {
    public static final String PATH_VAR_ID = "{id}";

    public static final String INDEX_ROUTE = Constant.STUDENT_ROUTE;
    public static final String ADD_ROUTE = INDEX_ROUTE + "/add";
    public static final String EDIT_ROUTE = INDEX_ROUTE + "/edit/" + PATH_VAR_ID;
    public static final String DELETE_ROUTE = INDEX_ROUTE + "/delete/" + PATH_VAR_ID;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebLinkFactory webLinkFactory;

    @Autowired
    APIBaseURI apiBaseURI;

    @GetMapping(INDEX_ROUTE)
    public String index(Model model) {
        ResponseEntity<List<Student>> studentResponse =
                restTemplate.exchange(apiBaseURI.getStudentApi(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                        });

        List<Student> students = studentResponse.getBody();
        model.addAttribute("students", students);
        return "student/index";
    }

    @GetMapping(ADD_ROUTE)
    public String add(Model model) {
        model.addAttribute("studentForm", new StudentForm());
        return "student/student-form";
    }

    @GetMapping(EDIT_ROUTE)
    public String edit(@PathVariable(value = "id") Long studentId, Model model) {
        StudentForm studentForm = restTemplate.getForObject(apiBaseURI.getStudentApi()+"/edit/" + studentId,
                StudentForm.class);
        model.addAttribute("studentForm", studentForm);
        return "student/student-form";
    }

    @PostMapping(ADD_ROUTE)
    public String add(Model model,
                      @Valid @ModelAttribute(value = "studentForm") StudentForm studentForm,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("studentForm", studentForm);
            return "student/student-form";
        } else {
            restTemplate.postForObject(apiBaseURI.getStudentApi()+"/add", studentForm, Student.class);
            return "redirect:" + webLinkFactory.studentIndex();
        }
    }
}
