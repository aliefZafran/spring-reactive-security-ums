package com.springbootassignment.ums.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RegisterDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}
