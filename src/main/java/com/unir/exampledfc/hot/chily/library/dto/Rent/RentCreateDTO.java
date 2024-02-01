package com.unir.exampledfc.hot.chily.library.dto.Rent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentCreateDTO {
    @NonNull
    private Long bookId;
    @NonNull
    private Long userId;
    private boolean rent;
    private int days;
}
