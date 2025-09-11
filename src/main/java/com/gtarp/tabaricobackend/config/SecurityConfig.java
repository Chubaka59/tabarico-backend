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
                        .requestMatchers(HttpMethod.POST, "/consumables/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.PUT, "/consumables/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.DELETE, "/consumables/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.GET, "/contracts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/contracts/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.PUT, "/contracts/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.DELETE, "/contracts/**").hasRole("Responsable")
                        .requestMatchers("/customerDirtySaleRates/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.GET, "/customersales/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/customersales/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/customersales/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.GET, "/exportersales/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/exportersales/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/exportersales/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.GET, "/roles/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/roles/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.PUT, "/roles/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.DELETE, "/roles/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.GET, "/stocks/**").hasAnyRole("CDI","Responsable")
                        .requestMatchers(HttpMethod.POST, "/stocks/**").hasAnyRole("CDI","Responsable")
                        .requestMatchers(HttpMethod.PUT, "/users/change-password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.POST, "/users/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.PUT, "/users/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.GET, "/personalDashboard/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/dashboard/**").hasRole("Responsable")
                        .requestMatchers(HttpMethod.PUT, "/dashboard/**").hasRole("Responsable")
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