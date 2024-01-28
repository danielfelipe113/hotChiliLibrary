package com.unir.exampledfc.HotChiliLibrary.repository;

import com.unir.exampledfc.HotChiliLibrary.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author, Long> {

}