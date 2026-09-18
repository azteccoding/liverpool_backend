package com.liverpool.restapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private int id;
    private String date;
    private List<OrderItemDTO> productsList;
    private Double total;
    private String paymentMethod;
    private Boolean isDispatched = false;
    private Boolean isCancelled = false;
}