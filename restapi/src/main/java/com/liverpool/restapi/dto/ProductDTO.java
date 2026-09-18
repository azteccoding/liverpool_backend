package com.liverpool.restapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private int id;
    private String imagePath;
    private String title;
    private String description;
}