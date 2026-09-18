package com.liverpool.restapi.controller;


import com.liverpool.restapi.dto.OrderDTO;
import com.liverpool.restapi.service.OrderService;
import domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/pedidos", produces = "application/json")
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping(value = "/pedido")
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO created = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = "/modificar/pedido/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable int orderId, @RequestBody OrderDTO orderDTO) {
        OrderDTO updated = orderService.updateOrder(orderId, orderDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping(value = "/modificar/pedido/{orderId}")
    public ResponseEntity<OrderDTO> patchOrder(@PathVariable int orderId, @RequestBody OrderDTO orderDTO) {
        OrderDTO updated = orderService.patchOrder(orderId, orderDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/borrar/{orderId}")
    public ResponseEntity<Map<String, String>> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Pedido id:" + orderId + " eliminado");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}