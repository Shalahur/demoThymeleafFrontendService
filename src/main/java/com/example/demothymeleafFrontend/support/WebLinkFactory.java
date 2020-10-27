package com.example.demothymeleafFrontend.support;

import com.example.demothymeleafFrontend.controller.StudentController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

public class WebLinkFactory {
    @Autowired
    private HttpServletRequest request;

    private UriComponentsBuilder getUriBuilder() {
        try {
            return ServletUriComponentsBuilder.fromServletMapping(request);
        } catch (Exception e) {
            try {
                return UriComponentsBuilder.fromUriString(URLEncoder.encode(request.getRequestURL().toString(), "UTF-8"))
                        .replacePath(StringUtils.EMPTY)
                        .replaceQuery(StringUtils.EMPTY)
                        .path(request.getContextPath() + "/");
            } catch (UnsupportedEncodingException e1) {
                return UriComponentsBuilder.fromPath(request.getContextPath() + "/");
            }
        }
    }

    // TODO: this need to be refactor
    public Locale getLocale() {
        return RequestContextUtils.getLocale(request);
    }

    public String getLanguage() {
        return getLocale().getLanguage();
    }

    private String build(String path) {
        return getUriBuilder().path(path).build().toString();
    }

    public String studentIndex() {
        return build(StudentController.INDEX_ROUTE);
    }

    public String addStudent() {
        return build(StudentController.ADD_ROUTE);
    }

    public String updateStudent(Long id) {
        return getUriBuilder().path(StudentController.EDIT_ROUTE
                .replace(StudentController.PATH_VAR_ID,
                        Long.toString(id))).toUriString();
    }

    public String deleteStudent(Long id) {
        return getUriBuilder().path(StudentController.DELETE_ROUTE
                .replace(StudentController.PATH_VAR_ID,
                        Long.toString(id))).toUriString();
    }

}
