package com.example.demo.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDataAccount {
    private String ra;
    private String name;
    private String email;
}
