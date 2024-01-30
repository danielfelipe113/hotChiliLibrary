package com.unir.exampledfc.HotChiliLibrary.service;

import com.unir.exampledfc.HotChiliLibrary.entity.User;
import com.unir.exampledfc.HotChiliLibrary.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> findById(Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public User save(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        return userRepository.save(newUser);
    }

    public User update(Long id, User user) {
        return userRepository.findById(id)
                .map(userToUpdate -> {
                    userToUpdate.setName(user.getName());
                    userToUpdate.setEmail(user.getEmail());
                    return userRepository.save(userToUpdate);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()).getBody();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
