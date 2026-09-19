package com.liverpool.restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = "text/html")
    public String home() {
        return "<h1>Este es el back de Liverpool para la UI de Liverpool</h1>" +
                "<p>API Liverpool funcionando. Endpoints: /pedidos, /pedido, /modificar/pedido/{id}, /borrar/pedido/{id}, /usuarios, /usuario</p>";
    }
}