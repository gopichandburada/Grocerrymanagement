package com.tcs.GroceryManagement.Encryption;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails admin = User.builder()
                .username("admin@gmail.com")
                .password(passwordEncoder().encode("admin@123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // 🔐 ADMIN ONLY
                .requestMatchers(HttpMethod.POST, "/product/AddProduct").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/product/Updateproduct").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/product/ActivateProduct").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/customer/ViewAllCustomers").hasRole("ADMIN")           
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults()); // ✅ FIXED

        return http.build();
    }
}
