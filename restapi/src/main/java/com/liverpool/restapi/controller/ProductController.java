package com.liverpool.restapi.controller;


import com.liverpool.restapi.dto.ProductDTO;
import com.liverpool.restapi.service.ProductService;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping(value="/products",produces = "application/json")
    public List<Product> getProducts() {
        return  productService.getAllProducts();
    }

    @PostMapping(value = "/product")
    public ResponseEntity  saveProduct(@RequestBody ProductDTO productDTO) {
            ProductDTO created = productService.createProduct(productDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity  updateProduct(@PathVariable int productId,@RequestBody ProductDTO productDTO){
        ProductDTO created = productService.updateProduct(productId,productDTO);
        return new ResponseEntity(created,HttpStatus.OK);

    }


    @DeleteMapping(value = "/delete/{productId}")
    public ResponseEntity  deleteProduct(@PathVariable int productId){

        Map<String, String> response = new HashMap<>();
        response.put("message", "Producto id:" + productId + " eliminado");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}