package com.example.demothymeleafFrontend.support;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class WebTemplateResolver {
    @Autowired
    private HttpServletRequest request;

    public String getLayout() {
        if (request.getRequestURI().equals(Constant.STUDENT_ROUTE)) {
            return "fragments/layout";
        } else {
            return "fragments/layout2";
        }
    }

}
