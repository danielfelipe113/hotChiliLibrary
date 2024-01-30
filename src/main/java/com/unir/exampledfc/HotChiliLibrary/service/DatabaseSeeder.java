package com.unir.exampledfc.HotChiliLibrary.service;

import com.unir.exampledfc.HotChiliLibrary.entity.User;
import com.unir.exampledfc.HotChiliLibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseSeeder {
    private final UserRepository userRepository;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void seedData() {
        User user1 = new User();
        user1.setName("User 1");
        user1.setEmail("user@one.com");

        User user2 = new User();
        user2.setName("User 2");
        user2.setEmail("user@two.com");

        userRepository.save(user1);
        userRepository.save(user2);

    }
}
