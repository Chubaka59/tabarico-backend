package com.gtarp.tabaricobackend.config;

import com.gtarp.tabaricobackend.services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Autowired
    private UserService userService;

    // 🔑 clé secrète pour signer le JWT (en pratique -> stocker dans application.properties ou un Vault)
    private static final String SECRET_KEY = "replace_this_with_a_very_long_and_secure_secret_key_1234567890";

    // ⚡ extrait le username depuis un token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // ⚡ extrait n'importe quelle information depuis le token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // ⚡ génère un token sans claims supplémentaires
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // ⚡ génère un token avec claims
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        com.gtarp.tabaricobackend.entities.User user = userService.getByUsername(userDetails.getUsername());
        extraClaims.put("username", userDetails.getUsername());
        extraClaims.put("firstName", user.getFirstName());
        extraClaims.put("lastName", user.getLastName());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) // username = identifiant
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24h
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ⚡ vérifie si le token est valide
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // ⚡ vérifie si le token est expiré
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ⚡ clé de signature dérivée de la SECRET_KEY
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
}
