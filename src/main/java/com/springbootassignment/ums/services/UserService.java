package com.springbootassignment.ums.services;

import com.springbootassignment.ums.models.MyUser;
import com.springbootassignment.ums.payload.RegisterDTO;
import com.springbootassignment.ums.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    //Constructor
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //user register
    public Mono<MyUser> createUser(RegisterDTO registerDTO){
        MyUser user = UserUtils.toUser(registerDTO);
        return userRepository.save(user);
    }

    //find a user
    public Mono<MyUser> readUser(int id){
        return userRepository.findById(id);
    }


    //get user
    public Flux<MyUser> list(){
        return userRepository.findAll();
    }
    public Mono<MyUser> loadUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    //update user details
    public Mono<MyUser> update(int id, MyUser userData){
        return userRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalMyUser -> {
                    if (optionalMyUser.isPresent()) {
                        userData.setId(id);
                        return userRepository.save(userData);
                    }

                    return Mono.empty();
                });
    }

    //delete user
    public Mono<Void> deleteUserID(int id){
        return userRepository.deleteById(id);
    }
    public Mono<Void> deleteUser(){
        return userRepository.deleteAll();
    }


    //Services
//
//    public Flux<MyUser> getUserByEmail(String email){
//        return userRepository.findByEmail(email);
//    }
//
//    public Mono<MyUser> findById(Long id){
//        return userRepository.findById(id);
//    }
//
//    public Mono<MyUser> newUser(MyUser newUser){
//        return userRepository.save(newUser);
//    }
//
//    public Flux<MyUser> getAll(){
//        return userRepository.findAll();
//    }




}
