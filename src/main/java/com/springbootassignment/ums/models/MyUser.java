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
    private String first_name;
    private String last_name;
    private String about;

    public MyUser(String email, String first_name, String last_name){
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

}
