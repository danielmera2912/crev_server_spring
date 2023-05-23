package com.example.crev_server_spring.security.jwt;

import com.example.crev_server_spring.modelo.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log
public class JwtProvider {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    @Value("${jwt.secret:D0sTr1st3sT1gr3C0m3nTr1g03n9nTr1g4lD0sTr1st3sT1gr3C0m3nTr1g03n9nTr1g4lD0sTr1st3sT1gr3C0m3nTr1g03n9nTr1g4l}")
    private String jwtSecret;
    @Value("${jwt.token-expiration:864000}")
    private int jwtDurationTokenSeconds;

    public String generateToken(Authentication authentication){
        Usuario user = (Usuario) authentication.getPrincipal();
        Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDurationTokenSeconds * 1000));
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ",TOKEN_TYPE)
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(tokenExpirationDate)
                .claim("email",user.getCorreo())
                .claim("username",user.getUsername())
                .claim("id", user.getId())
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.info("Error firma token JWT: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.info("Token malformado: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.info("Token expirado: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.info("Token JWT no soportado: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.info("JWT claims vacío");
        }
        return false;
    }
}