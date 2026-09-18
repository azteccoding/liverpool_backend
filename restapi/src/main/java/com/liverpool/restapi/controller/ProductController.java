package com.liverpool.restapi.controller;


import com.liverpool.restapi.dto.ProductDTO;
import com.liverpool.restapi.service.ProductService;
import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @DeleteMapping(value = "/{productId}")
    public ResponseEntity  deleteProduct(@PathVariable int productId){

        productService.deleteProduct(productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }
}