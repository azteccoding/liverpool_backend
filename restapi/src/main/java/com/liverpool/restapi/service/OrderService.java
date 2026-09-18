package com.liverpool.restapi.service;

import com.liverpool.restapi.dto.OrderDTO;
import com.liverpool.restapi.repository.OrderRepository;
import domain.Order;
import domain.model.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class OrderService implements CrudService<Order> {

    @Autowired
    OrderRepository repository;

    @Override
    public void create(Order order) {
        repository.save(order);
    }

    @Override
    public void update(String id, Order order) {
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public Collection<Order> getItem() {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    // ---- Métodos adicionales que usa el controller ----

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order(
                orderDTO.getId(),
                orderDTO.getImagePath(),
                orderDTO.getTitle(),
                orderDTO.getDescription()
        );

        Order saved = repository.save(order);
        return mapToDTO(saved);
    }

    public OrderDTO updateOrder(int productId, OrderDTO orderDTO) {
        Order order = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productId));

        order.setImagePath(orderDTO.getImagePath());
        order.setTitle(orderDTO.getTitle());
        order.setDescription(orderDTO.getDescription());

        Order updated = repository.save(order);
        return mapToDTO(updated);
    }

    public void deleteOrder(int orderId) {
        repository.deleteById(orderId);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setImagePath(order.getImagePath());
        dto.setTitle(order.getTitle());
        dto.setDescription(order.getDescription());
        return dto;
    }
}