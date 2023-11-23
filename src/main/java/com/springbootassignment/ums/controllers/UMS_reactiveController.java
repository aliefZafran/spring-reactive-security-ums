package com.springbootassignment.ums.controllers;

import com.springbootassignment.ums.models.MyUser;
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

    @GetMapping("/test")
    public String testApi(){
        return "hello world";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MyUser> register(@RequestBody RegisterDTO user){
        return userService.createUser(user);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MyUser> getUserDetails(@PathVariable int id){
        return userService.readUser(id);
    }

    @GetMapping("/users")
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
    @ResponseStatus(HttpStatus.OK)
    public Mono<MyUser> loadUser(@RequestBody String email){
        return userService.loadUserByEmail(email);
    }

}
