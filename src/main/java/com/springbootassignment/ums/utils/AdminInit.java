//package com.springbootassignment.ums.utils;
//
//import com.springbootassignment.ums.models.MyUser;
//import com.springbootassignment.ums.services.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Component
//public class AdminInit implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    public AdminInit(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        userRepository.findByRole("ADMIN")
//                .switchIfEmpty(createAdminAccount())
//                .subscribe();
//    }
//
//    private Mono<MyUser> createAdminAccount() {
//        String adminEmail = "adminuser@email.com"; // Replace with your admin email
//        String adminPassword = "admin1234"; // Replace with your admin password
//        String adminFirstname = "admin";
//        String adminLastname = "user";
//
//        MyUser adminUser = new MyUser(adminEmail, passwordEncoder.encode(adminPassword), adminFirstname, adminLastname);
//        adminUser.setRole("ADMIN");
//        adminUser.setDisabled(false);
//        return userRepository.save(adminUser);
//    }
//}
