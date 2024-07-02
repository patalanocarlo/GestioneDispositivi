package CarloPatalano.GestioneDispositivi.Tools;

import CarloPatalano.GestioneDispositivi.DTO.UnauthorizedException;
import CarloPatalano.GestioneDispositivi.Entities.Dipendente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(Dipendente dipendente) {
        return Jwts.builder()
                .setSubject(dipendente.getId().toString())
                .claim("role", dipendente.getRole().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public Long extractIdFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public void verifyToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");
        }
    }
}