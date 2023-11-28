package com.springbootassignment.ums.controllers;

import com.springbootassignment.ums.models.MyUser;
import com.springbootassignment.ums.payload.LoginDTO;
import com.springbootassignment.ums.payload.RegisterDTO;
import com.springbootassignment.ums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UMS_reactiveController {

    UserService userService;

    @Autowired
    public UMS_reactiveController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/test") //protected
    public String testApi(){
        return "hello world";
    }

    @PostMapping("/register") //public
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MyUser> register(@RequestBody RegisterDTO user){
        return userService.createUser(user);
    }

    @GetMapping("/user/{id}") //protected
    @ResponseStatus(HttpStatus.OK)
    public Mono<MyUser> getUserDetails(@PathVariable int id){
        return userService.readUser(id);
    }

    @GetMapping("/users") //public
    @ResponseStatus(HttpStatus.OK)
    public Flux<MyUser> getAllUsers(){
        return userService.list();
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MyUser> updateUser(@PathVariable int id, @RequestBody MyUser updUser){
        return userService.update(id, updUser);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUser(@PathVariable int id){
        return userService.deleteUserID(id);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUsers(){
        return userService.deleteUser();
    }

    @PostMapping("/login")
    public Mono<MyUser> loadUser(@RequestBody LoginDTO loginDTO){
        String userEmail = loginDTO.getEmail();
        return userService.loadUserByEmail(userEmail);
    }

}
