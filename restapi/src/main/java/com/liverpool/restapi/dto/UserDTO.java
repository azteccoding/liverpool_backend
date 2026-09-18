package com.liverpool.restapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private Boolean liverpoolCardHolder;
}