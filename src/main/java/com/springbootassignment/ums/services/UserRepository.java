package com.springbootassignment.ums.services;

import com.springbootassignment.ums.models.MyUser;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<MyUser, Integer> {
    Mono<MyUser> findByEmail(String email);

    @Query("SELECT * FROM MY_USER u WHERE u.verification_code = :code")
    Mono<MyUser> findByVerificationCode(String code);

    @Query("SELECT * FROM MY_USER u WHERE u.reset_token = :token")
    Mono<MyUser> findByResetToken(String token);

    @Query("SELECT * FROM MY_USER u WHERE u.role = :role")
    Mono<MyUser> findByRole(String role);
}
