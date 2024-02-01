package com.unir.exampledfc.hot.chily.library.repository;

import com.unir.exampledfc.hot.chily.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}