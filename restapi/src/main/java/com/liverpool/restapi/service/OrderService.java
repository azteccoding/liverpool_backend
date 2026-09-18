package com.liverpool.restapi.service;

import com.liverpool.restapi.dto.OrderDTO;
import com.liverpool.restapi.dto.OrderItemDTO;
import com.liverpool.restapi.repository.OrderRepository;
import domain.Order;
import domain.OrderItem;
import domain.model.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Order> getAll() {
        return repository.findAll();
    }

    // ---- Métodos adicionales que usa el controller ----

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order(
                orderDTO.getId(),
                orderDTO.getDate(),
                mapToItemEntities(orderDTO.getProductsList()),
                orderDTO.getTotal(),
                orderDTO.getPaymentMethod(),
                orderDTO.getIsDispatched(),
                orderDTO.getIsCancelled()
        );

        Order saved = repository.save(order);
        return mapToDTO(saved);
    }

    public OrderDTO patchOrder(int orderId, OrderDTO orderDTO) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + orderId));

        if (orderDTO.getDate() != null) {
            order.setDate(orderDTO.getDate());
        }
        if (orderDTO.getProductsList() != null) {
            order.setProductsList(mapToItemEntities(orderDTO.getProductsList()));
        }
        if (orderDTO.getTotal() != null) {
            order.setTotal(orderDTO.getTotal());
        }
        if (orderDTO.getPaymentMethod() != null) {
            order.setPaymentMethod(orderDTO.getPaymentMethod());
        }
        if (orderDTO.getIsDispatched() != null) {
            order.setIsDispatched(orderDTO.getIsDispatched());
        }
        if (orderDTO.getIsCancelled() != null) {
            order.setIsCancelled(orderDTO.getIsCancelled());
        }

        Order updated = repository.save(order);
        return mapToDTO(updated);
    }

    public OrderDTO updateOrder(int orderId, OrderDTO orderDTO) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado: " + orderId));

        order.setDate(orderDTO.getDate());
        order.setProductsList(mapToItemEntities(orderDTO.getProductsList()));
        order.setTotal(orderDTO.getTotal());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        order.setIsDispatched(orderDTO.getIsDispatched());
        order.setIsCancelled(orderDTO.getIsCancelled());

        Order updated = repository.save(order);
        return mapToDTO(updated);
    }

    public void deleteOrder(int orderId) {
        repository.deleteById(orderId);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setProductsList(mapToItemDTOs(order.getProductsList()));
        dto.setTotal(order.getTotal());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setIsDispatched(order.getIsDispatched());
        dto.setIsCancelled(order.getIsCancelled());
        return dto;
    }
    // para guardar en mongo
    private List<OrderItem> mapToItemEntities(List<OrderItemDTO> items) {
        return items.stream()
                .map(i -> new OrderItem(
                        i.getSku(),
                        i.getQuantity(),
                        i.getProductName(),
                        i.getUnitPrice(),
                        i.getTotalPrice()
                ))
                .collect(Collectors.toList());
    }
    // para devolver el JSON de respuesta exitosa
    private List<OrderItemDTO> mapToItemDTOs(List<OrderItem> items) {
        return items.stream()
                .map(i -> {
                    OrderItemDTO dto = new OrderItemDTO();
                    dto.setSku(i.getSku());
                    dto.setQuantity(i.getQuantity());
                    dto.setProductName(i.getProductName());
                    dto.setUnitPrice(i.getUnitPrice());
                    dto.setTotalPrice(i.getTotalPrice());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}