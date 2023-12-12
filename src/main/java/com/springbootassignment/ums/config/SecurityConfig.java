package com.springbootassignment.ums.config;

import com.springbootassignment.ums.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        authenticationManager.setPasswordEncoder(passwordEncoder);
        return authenticationManager;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http
                .authorizeExchange(ex -> ex
                        .pathMatchers(HttpMethod.POST, "/api/register").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .pathMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api-docs").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/verify").permitAll()
                        .pathMatchers(HttpMethod.GET, "/api/reset-password").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/reset-password").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/forgot-password").permitAll()
                        .pathMatchers(HttpMethod.DELETE, "/api/users").hasRole("ADMIN")
                        .pathMatchers(HttpMethod.DELETE, "/api/user/{id}").hasRole("ADMIN")
                        .anyExchange().authenticated())
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .httpBasic(withDefaults())
                .formLogin(withDefaults())
                .build();
    }
}


//    private final UserDetailsServiceImpl userDetailsService;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    public SecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder passwordEncoder){
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Bean
//    public ReactiveUserDetailsService userDetailsService() {
//        return userDetailsService;
//    }
//    @Bean
//    public MapReactiveUserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(user);
//    }
