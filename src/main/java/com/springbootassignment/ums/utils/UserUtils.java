package com.springbootassignment.ums.utils;

import com.springbootassignment.ums.models.MyUser;
import com.springbootassignment.ums.payload.RegisterDTO;

public class UserUtils {

    public static MyUser toUser(RegisterDTO registerUser){
        return new MyUser(registerUser.getEmail(), registerUser.getFirst_name(), registerUser.getLast_name());
    }

}
