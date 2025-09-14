package com.gtarp.tabaricobackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // 🔥 active la config définie dans CorsConfig
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/consumables/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/consumables/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.PUT, "/consumables/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.DELETE, "/consumables/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.GET, "/contracts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/contracts/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.PUT, "/contracts/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.DELETE, "/contracts/**").hasRole("Patron")
                        .requestMatchers("/customerDirtySaleRates/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.GET, "/customersales/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/customersales/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/customersales/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.GET, "/exportersales/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/exportersales/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/exportersales/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.GET, "/roles/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/roles/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.PUT, "/roles/**").hasRole("Patron")
                        .requestMatchers(HttpMethod.GET, "/stocks/**").hasAnyRole("CDI","Responsable","Patron")
                        .requestMatchers(HttpMethod.POST, "/stocks/**").hasAnyRole("CDI","Responsable","Patron")
                        .requestMatchers(HttpMethod.PUT, "/users/change-password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("Responsable", "Patron")
                        .requestMatchers(HttpMethod.POST, "/users/**").hasAnyRole("Responsable", "Patron")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasAnyRole("Responsable", "Patron")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyRole("Responsable", "Patron")
                        .requestMatchers(HttpMethod.GET, "/personalDashboard/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dashboard/**").hasAnyRole("Responsable", "Patron")
                        .requestMatchers(HttpMethod.PUT, "/dashboard/**").hasAnyRole("Responsable", "Patron")
                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}