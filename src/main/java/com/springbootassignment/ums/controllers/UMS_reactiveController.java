package com.springbootassignment.ums.controllers;

import com.springbootassignment.ums.models.MyUser;
import com.springbootassignment.ums.payload.RegisterDTO;
import com.springbootassignment.ums.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UMS_reactiveController {

    UserService userService;

    @Autowired
    public UMS_reactiveController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test") //protected
    public String testApi() {
        return "hello world";
    }

    @PostMapping("/register")
    public Mono<String> registerUser(@RequestBody RegisterDTO registerDTO){
        return userService.create(registerDTO).thenReturn("Registration Successful, please verify your account in your email.");
    }

    @GetMapping("/verify")
    public Mono<String> verifyUser(@RequestParam("code") String code) {
        return userService.verify(code)
                .map(verificationStatus -> {
                    if (verificationStatus) {
                        return "verify success";
                    } else {
                        return "verify fail";
                    }
                });
    }

    @PostMapping("/forgot-password")
    public Mono<String> forgotPassword(@RequestBody MyUser user){
        return userService.forgotPassword(user.getEmail());
    }

    @PostMapping("/reset-password")
    public Mono<String> resetPassword(@RequestParam("token") String token, @RequestBody MyUser user) {
        return userService.resetPassword(token, user.getPassword());
    }

    @GetMapping("/reset-password")
    public Mono<String> resetPassword(@RequestParam("token") String token) {
        return userService.getToken(token);
    }

    @GetMapping("/user/{id}") //protected
    @ResponseStatus(HttpStatus.OK)
    public Mono<MyUser> getUserDetails(@PathVariable int id) {
        return userService.getUser(id);
    }


    @GetMapping("/users") //public
    @ResponseStatus(HttpStatus.OK)
    public Flux<MyUser> getAllUsers() {
        return userService.list();
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MyUser> updateUser(@PathVariable int id, @RequestBody MyUser updUser) {
        return userService.update(id, updUser);
    }

    @DeleteMapping("/user/{id}")
    public Mono<String> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUsers() {
        return userService.delete();
    }

}
