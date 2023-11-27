package project.weather_stock_api.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import project.weather_stock_api.dto.response.UserResponse;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {

    @Value("${security.jwt.secret}")
    private String SECRET_KEY;

   public String findUsername(String jwt) {
       return exportToken(jwt, Claims::getSubject);}

        public  <T> T exportToken(String jwt, Function<Claims, T> claimsTFunction) {
            if (jwt == null || jwt.isEmpty()) {
                throw new IllegalArgumentException("JWT token cannot be null or empty");
            }
            final Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build().parseClaimsJws(jwt).getBody();
            return claimsTFunction.apply(claims);
        }

    private Key getKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }


    public boolean tokenControl(String jwt, UserDetails userDetails) {
        final String username = findUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !exportToken(jwt, Claims::getExpiration).before(new Date()));
    }

    public String generateToken(UserDetails userDetails) {

         String token=Jwts.builder()
        .setClaims(new HashMap<>())
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
         log.info("Generated token  "+token);
        return token;
    }
}
