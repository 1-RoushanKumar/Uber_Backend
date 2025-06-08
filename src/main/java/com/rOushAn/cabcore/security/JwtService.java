package com.rOushAn.cabcore.security;

import com.rOushAn.cabcore.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    private SecretKey getSecretKey() {
        if (secretKey == null || secretKey.length() < 32) {
            throw new IllegalArgumentException("Secret key must be at least 32 characters long for HS256:" + secretKey.length() + secretKey);
        }
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generates a short-lived Access JWT Token.
     * This token is used to authorize access to protected API endpoints.
     *
     * @param user The authenticated user for whom the token is being generated.
     * @return A JWT access token containing user ID, email, and roles with a 1-hour expiry.
     */
    public String getAccessJwtToken(User user) {
        return Jwts
                .builder()
                // Set the user ID as the subject of the token (used to identify the user).
                .subject(String.valueOf(user.getId()))

                // Add custom claims to the token for additional user data.
                .claim("email", user.getEmail())     // User's email
                .claim("role", user.getRoles().toString()) // User's role(s)

                // Set the token issue time to current time.
                .issuedAt(new Date())

                // Set the expiration time to 1 hour from now.
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))

                // Sign the token with a secret key (HMAC-SHA algorithm by default).
                .signWith(getSecretKey())

                // Build and return the compact JWT string.
                .compact();
    }

    /**
     * Generates a long-lived Refresh JWT Token.
     * This token is used to generate a new access token when the current one expires.
     *
     * @param user The authenticated user for whom the token is being generated.
     * @return A JWT refresh token with a 6-month expiry.
     */
    public String getRefreshJwtToken(User user) {
        return Jwts
                .builder()
                // Set the user ID as the subject of the token.
                .subject(String.valueOf(user.getId()))

                // Set the token issue time.
                .issuedAt(new Date())

                // Set the expiration time to 6 months from now.
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30 * 6))

                // Sign the token with the same secret key.
                .signWith(getSecretKey())

                // Build and return the compact JWT string.
                .compact();
    }

    public Long getUserId(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(claims.getSubject());
    }
}
