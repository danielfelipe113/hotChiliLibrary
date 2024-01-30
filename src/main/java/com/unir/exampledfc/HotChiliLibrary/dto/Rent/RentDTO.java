package com.unir.exampledfc.HotChiliLibrary.dto.Rent;


import com.unir.exampledfc.HotChiliLibrary.entity.Rent;
import com.unir.exampledfc.HotChiliLibrary.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentDTO {
    private Long id;
    private User user;
    private Long bookId;
    private int rentedDays;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    public RentDTO(Rent rent) {
        this.id = rent.getId();
        this.user = rent.getUser();
        this.bookId = rent.getBookId();
        this.rentedDays = rent.getRentedDays();
        this.rentalDate = rent.getRentalDate();
        this.returnDate = rent.getReturnDate();

    }
}
