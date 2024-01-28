package com.unir.exampledfc.HotChiliLibrary.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {
    private String name;
    private String email;
    private String password;
}
