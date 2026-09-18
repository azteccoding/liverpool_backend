package com.liverpool.restapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private String sku;
    private int quantity;
    private String productName;
    private double unitPrice;
    private double totalPrice;
}