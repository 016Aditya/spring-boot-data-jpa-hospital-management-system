package com.LearnSpringBoot.hospitalManagementSystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 1. CRITICAL FOR REST APIs: Disable CSRF so tools like Postman can make POST requests
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Explicitly define route permissions
                .authorizeHttpRequests(auth -> auth
                        // Allow everyone (logged in or not) to access the landing index.html page
                        .requestMatchers("/", "/index.html", "/static/**").permitAll()

                        // Restrict the Admin Controller explicitly to users with the 'ADMIN' role
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // All other patient profile or appointment endpoints require authentication
                        .anyRequest().authenticated()
                )

                // 3. Keep your standard login form interface active
                .formLogin(Customizer.withDefaults())

                // 4. Fallback HTTP Basic support (handy for debugging via Postman)
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1 = User.withUsername("admin")
//                .password(passwordEncoder.encode("pass")) // Password is "pass"
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2 = User.withUsername("patient")
//                .password(passwordEncoder.encode("pass")) // Password is "pass"
//                .roles("PATIENT")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}