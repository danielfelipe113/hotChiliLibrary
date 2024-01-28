package com.unir.exampledfc.HotChiliLibrary.repository;

import com.unir.exampledfc.HotChiliLibrary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}