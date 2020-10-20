package com.example.demothymeleafFrontend.form;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder", toBuilder = true)
public class StudentForm {
    private Long id;

    private String name;

    private String fatherName;

    private String motherName;
}
