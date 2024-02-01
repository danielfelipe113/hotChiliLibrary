package com.unir.exampledfc.hot.chily.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private User user;

    private Long bookId; // Reference to the book by bookId

    private int rentedDays;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

}
