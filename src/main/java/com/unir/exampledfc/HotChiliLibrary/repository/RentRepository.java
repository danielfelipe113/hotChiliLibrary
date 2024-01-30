package com.unir.exampledfc.HotChiliLibrary.repository;

import com.unir.exampledfc.HotChiliLibrary.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByBookId(Long bookId);
}