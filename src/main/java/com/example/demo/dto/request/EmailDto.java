package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailDto {

    private String name;
    private String emailTo;
    private String password;
}
