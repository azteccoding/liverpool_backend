package com.liverpool.restapi.service;

import com.liverpool.restapi.dto.ProductDTO;
import com.liverpool.restapi.repository.ProductRepository;
import domain.Product;
import domain.model.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductService implements CrudService<Product> {

    @Autowired
    ProductRepository repository;

    @Override
    public void create(Product product) {
        repository.save(product);
    }

    @Override
    public void update(String id, Product product) {
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public Collection<Product> getItem() {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // ---- Métodos adicionales que usa el controller ----

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product(
                productDTO.getId(),
                productDTO.getImagePath(),
                productDTO.getTitle(),
                productDTO.getDescription()
        );

        Product saved = repository.save(product);
        return mapToDTO(saved);
    }

    public ProductDTO updateProduct(int productId, ProductDTO productDTO) {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + productId));

        product.setImagePath(productDTO.getImagePath());
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());

        Product updated = repository.save(product);
        return mapToDTO(updated);
    }

    public void deleteProduct(int productId) {
        repository.deleteById(productId);
    }

    private ProductDTO mapToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setImagePath(product.getImagePath());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        return dto;
    }
}