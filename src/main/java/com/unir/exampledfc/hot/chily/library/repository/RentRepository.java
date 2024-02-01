package com.unir.exampledfc.hot.chily.library.repository;

import com.unir.exampledfc.hot.chily.library.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByBookId(Long bookId);
}