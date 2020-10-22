package com.example.demothymeleafFrontend.support;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class APIBaseURI {

    @Value("${application.base.uri.student.api:http://localhost:8080/student}")
    public String studentApi;
}
