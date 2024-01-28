package com.unir.exampledfc.HotChiliLibrary.service;

import com.unir.exampledfc.HotChiliLibrary.dto.User.UserCreateDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.User.UserDTO;
import com.unir.exampledfc.HotChiliLibrary.dto.User.UserUpdateDTO;
import com.unir.exampledfc.HotChiliLibrary.entity.User;
import com.unir.exampledfc.HotChiliLibrary.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public UserDTO save(UserCreateDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // Password handling should be more secure in real applications
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
    }

    public UserDTO update(Long id, UserUpdateDTO userUpdateDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userUpdateDTO.getName());
                    user.setEmail(userUpdateDTO.getEmail());
                    return userRepository.save(user);
                })
                .map(updatedUser -> new UserDTO(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()).getBody();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
