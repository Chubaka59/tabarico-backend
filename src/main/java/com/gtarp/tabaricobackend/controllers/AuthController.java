package com.gtarp.tabaricobackend.controllers;

import com.gtarp.tabaricobackend.config.JwtService;
import com.gtarp.tabaricobackend.dto.AuthRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        String role = user.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("refreshToken", refreshToken);
        map.put("role", role);

        return ResponseEntity.ok(map);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String username = jwtService.extractUsername(refreshToken);

        // ⚡ Vérifie si refreshToken est encore valide
        if (!jwtService.isTokenExpired(refreshToken)) {
            UserDetails user = userDetailsService.loadUserByUsername(username);
            String newAccessToken = jwtService.generateToken(user);

            Map<String, String> response = new HashMap<>();
            response.put("token", newAccessToken);
            response.put("refreshToken", refreshToken); // on renvoie le même refresh tant qu’il est valide
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Refresh token expired"));
        }
    }
}
