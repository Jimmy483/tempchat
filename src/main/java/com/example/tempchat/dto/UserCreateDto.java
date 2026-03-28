package com.example.tempchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    private String username;

    private String password;

    private String fName;

    private String lName;
}
