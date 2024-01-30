package com.unir.exampledfc.HotChiliLibrary.dto.Rent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentUpdateDTO {
    private Long bookId;
    private int days;
}
