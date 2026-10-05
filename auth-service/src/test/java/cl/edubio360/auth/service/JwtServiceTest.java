package cl.edubio360.auth.service;

import cl.edubio360.auth.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    @Test
    void generaTokenConUsuarioRolYExpiracion() {
        String secret = "edubio360-test-secret-key-123456789012345";
        JwtService service = new JwtService(secret, 30);
        UserEntity user = new UserEntity("student@example.test", "hash", "STUDENT");

        String token = service.createToken(user);

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        assertEquals("student@example.test", claims.getSubject());
        assertEquals("STUDENT", claims.get("role", String.class));
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
        assertTrue(claims.getExpiration().after(claims.getIssuedAt()));
    }
}
