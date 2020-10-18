package com.example.demothymeleafFrontend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder", toBuilder = true)
public class Student {

    private Long id;

    private String name;

    private String fatherName;

    private String motherName;

}
