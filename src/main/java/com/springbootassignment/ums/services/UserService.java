package com.springbootassignment.ums.services;

import com.springbootassignment.ums.models.MyUser;
import com.springbootassignment.ums.payload.RegisterDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    //Constructor
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    //user register
    public Mono<MyUser> create(RegisterDTO registerDTO){
        String randomCode = UUID.randomUUID().toString();
        String encryptPw = passwordEncoder.encode(registerDTO.getPassword());
        MyUser user = new MyUser(registerDTO.getEmail(), encryptPw, registerDTO.getFirstName(), registerDTO.getLastName());
        user.setRole("USER");
        user.setDisabled(true);
        user.setVerificationCode(randomCode);

        return userRepository.save(user)
                .flatMap(newUser -> {
                    return sendVerificationEmail(newUser);
                })
                .onErrorMap(e -> new RuntimeException("Failed to  register user: " + e.getMessage()));
    }

    private Mono<MyUser> sendVerificationEmail(MyUser user) {
        try {
            emailService.sendVerificationEmail(user);
            return Mono.just(user);
        } catch (MessagingException | UnsupportedEncodingException e) {
            return Mono.error(new RuntimeException("Fail to send verification mail: " + e.getMessage()));
        }
    }

    public Mono<Boolean> verify(String verificationCode) {
        return userRepository.findByVerificationCode(verificationCode)
                .flatMap(user -> {
                    user.setVerificationCode(null);
                    user.setDisabled(false);
                    return userRepository.save(user).thenReturn(true);
                })
                .defaultIfEmpty(false)
                .onErrorMap(e -> new RuntimeException("Couldn't verify account"));
    }

    public Mono<String> forgotPassword(String userEmail) {
        return userRepository.findByEmail(userEmail)
                .flatMap(user -> {
                    String resetToken = UUID.randomUUID().toString();
                    user.setResetToken(resetToken);
                    return userRepository.save(user).thenReturn(user);
                })
                .flatMap(savedUser -> {
                    return sendResetPassword(savedUser);
                })
                .defaultIfEmpty("Fail to send reset password email")
                .onErrorMap(e -> new RuntimeException("Something went wrong: " + e.getMessage()));
    }

    private Mono<String> sendResetPassword(MyUser user){
        try {
            emailService.sendResetPasswordEmail(user);
            return Mono.just("Password reset link sent successfully.");
        } catch (MessagingException | UnsupportedEncodingException e) {
            // Handle the exception appropriately
            return Mono.error(e);
        }
    }

    public Mono<String> resetPassword(String token, String newPassword) {
        return userRepository.findByResetToken(token)
                .flatMap(user -> {
                    String encryptPw = passwordEncoder.encode(newPassword);
                    user.setPassword(encryptPw);
                    user.setResetToken(null);
                    return userRepository.save(user).thenReturn("Password reset successfully.");

                })
                .defaultIfEmpty("Invalid token.")
                .onErrorMap(e -> new RuntimeException("Something went wrong: " + e.getMessage()));
    }

    //print link to reset password; used to send post request in postman
    public Mono<String> getToken(String token) {
        return userRepository.findByResetToken(token)
                .thenReturn("http://localhost:8000/api/reset-password?token=" + token);
    }


    //find a user
    public Mono<MyUser> getUser(int id) {
        return userRepository.findById(id)
                .onErrorMap(e -> new RuntimeException("Cannot find user with id: " + id));
    }

    //get user
    public Flux<MyUser> list() {
        return userRepository.findAll();
    }


    public Mono<MyUser> update(int id, MyUser user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    // Use reflection to ge-t all fields
                    Field[] fields = MyUser.class.getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true);
                        try {
                            // Get the value of the field from theUser
                            Object value = field.get(user);
                            // Update the field in existingUser only if the value is not null
                            if (value != null) {
                                field.set(existingUser, value);
                            }
                        } catch (IllegalAccessException e) {
                            return Mono.error(e); // Propagate the error as Mono.error
                        }
                    }

                    // Set the ID before saving
                    existingUser.setId(id);
                    //update the data to db
                    return userRepository.save(existingUser);
                })
                .onErrorMap(e -> new RuntimeException(e.getMessage()));
    }


    //delete user
    public Mono<String> deleteUser(int id) {
        return userRepository.findById(id)
                .flatMap(user -> {
                    userRepository.deleteById(id);
                    return Mono.just("User with id " + id + " has been deleted");
                })
                .defaultIfEmpty("User with id " + id + " not found");
    }

    public Mono<Void> delete() {
        return userRepository.deleteAll();
    }
}