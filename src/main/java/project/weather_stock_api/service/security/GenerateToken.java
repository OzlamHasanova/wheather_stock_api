//package project.weather_stock_api.service.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.Data;
//import lombok.Getter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import project.weather_stock_api.entity.User;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class GenerateToken {
//    @Value("${security.jwt.secret}")
//    private String SECRET_KEY;
//    private Key getKey() {
//        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(key);
//    }
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("sub", userDetails.getUsername());
//        if (userDetails instanceof User) {
//            User user = (User) userDetails;
//            claims.put("email", user.getEmail());
//        }
//        System.out.println("dsdsfdsd"+ Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
//                .signWith(getKey(), SignatureAlgorithm.HS256)
//                .compact());
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
//                .signWith(getKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//}
