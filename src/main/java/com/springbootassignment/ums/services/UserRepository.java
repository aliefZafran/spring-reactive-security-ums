package com.springbootassignment.ums.services;

import com.springbootassignment.ums.models.MyUser;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<MyUser, Integer> {
    Mono<MyUser> findByEmail(String email);
}
