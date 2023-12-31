package io.apirest.estacionamento.java.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = "132468096-987541230-57689031240a";
    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 1;
    public static final long EXPIRE_MINUTES = 30;

    private JwtUtils() { }

    public static JwtToken createToken(String username, String role) {
        Date issueAt = new Date();
        Date limit = toExpireDate(issueAt);

        String token = Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setSubject(username)
            .setIssuedAt(issueAt)
            .setExpiration(limit)
            .signWith(generateKey(), SignatureAlgorithm.HS256)
            .claim("role", role)
            .compact();

        return new JwtToken(token);
    }

    // Responsável por recuperar o Username do Token (usa outro método para parte do serviço)
    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    // Responsável para verificar a validade do Token
    public static boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(generateKey()).build()
                .parseClaimsJws(refactorToken(token));
            return true;

        } catch (JwtException ex) {
            log.error(String.format("Token inválido: %s", ex.getMessage()));
        }
        return false;
    }

    // Responsável por preparar a nossa chave para ser criptografada no momento de gerar o Token
    private static Key generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Responsável por fonecer a data-hora de expiração do Token
    private static Date toExpireDate(Date start) {
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    // Responsável por recuperar o conteúdo do Token. O conteúdo será retirado da interface devolvida.
    private static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                .setSigningKey(generateKey()).build()
                .parseClaimsJws(refactorToken(token)).getBody();

        } catch (JwtException ex) {
            log.error(String.format("Token inválido: %s", ex.getMessage()));
        }
        return null;
    }

    // Responsável por retirar "Bearer " do token
    private static String refactorToken(String token) {
        if (token.contains(JWT_BEARER))
            return token.substring(JWT_BEARER.length());
        return token;
    }
}

