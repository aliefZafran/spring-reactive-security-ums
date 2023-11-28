package com.springbootassignment.ums.models;


import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MyUser {
    @Id
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String about;

    public MyUser(String email, String password, String firstName, String lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
