package com.liverpool.restapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private int id;
    private String imagePath;
    private String title;
    private String description;
}