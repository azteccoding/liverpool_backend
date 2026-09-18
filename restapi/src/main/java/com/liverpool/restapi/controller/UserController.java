package com.liverpool.restapi.controller;

import com.liverpool.restapi.dto.UserDTO;
import com.liverpool.restapi.service.UserService;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/usuarios", produces = "application/json")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping(value = "/usuario")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO created = userService.createUser(userDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/modificar/usuario/{userId}")
    public ResponseEntity<UserDTO> patchUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
        UserDTO updated = userService.patchUser(userId, userDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(value = "/borrar/usuario/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario id:" + userId + " eliminado");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}