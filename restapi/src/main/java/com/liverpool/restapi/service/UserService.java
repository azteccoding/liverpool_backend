package com.liverpool.restapi.service;

import com.liverpool.restapi.dto.UserDTO;
import com.liverpool.restapi.repository.UserRepository;
import domain.User;
import domain.model.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements CrudService<User> {

    @Autowired
    UserRepository repository;

    @Override
    public void create(User user) {
        repository.save(user);
    }

    @Override
    public void update(String id, User user) {
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public Collection<User> getItem() {
        return null;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getLiverpoolCardHolder()
        );
        User saved = repository.save(user);
        return mapToDTO(saved);
    }

    public UserDTO patchUser(int userId, UserDTO userDTO) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + userId));

        if (userDTO.getName() != null) user.setName(userDTO.getName());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getPassword() != null) user.setPassword(userDTO.getPassword());
        if (userDTO.getLiverpoolCardHolder() != null) user.setLiverpoolCardHolder(userDTO.getLiverpoolCardHolder());

        User updated = repository.save(user);
        return mapToDTO(updated);
    }

    public void deleteUser(int userId) {
        repository.deleteById(userId);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setLiverpoolCardHolder(user.getLiverpoolCardHolder());
        return dto;
    }
}