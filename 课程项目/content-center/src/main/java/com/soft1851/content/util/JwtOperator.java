package com.soft1851.content.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/13
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtOperator {
    /**
     *
     */
    @Value("${secret:aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa}")
    private String secret;
    /**
     *
     *
     */
    @Value("${expire-time-in-second:1209600}")
    private Long expirationTimeInSecond;

    /**
     *
     *
     * @param token token
     * @return claim
     */
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(this.secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            log.error("token", e);
            throw new IllegalArgumentException("Token invalided.");
        }
    }

    /**
     *
     *
     * @param token token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }

    /**
     *
     *
     * @param token token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     *
     *
     * @return
     */
    public Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + this.expirationTimeInSecond * 1000);
    }

    /**
     *
     *
     * @param claims
     * @return token
     */
    public String generateToken(Map<String, Object> claims) {
        Date createdTime = new Date();
        Date expirationTime = this.getExpirationTime();

        byte[] keyBytes = secret.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdTime)
                .setExpiration(expirationTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     *
     *
     * @param token token
     * @return
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    //public static void main(String[] args) {
    // 1.
    //JwtOperator jwtOperator = new JwtOperator();
    //jwtOperator.expirationTimeInSecond = 1209600L;
    //jwtOperator.secret = "aaaaaaabbbbbbcccccdddddaaaaaaabbbbbbcccccdddddaaaaaaabbbbbbcccccddddd";

    // 2.
    //HashMap<String, Object> objectObjectHashMap = Maps.newHashMap();
    //objectObjectHashMap.put("id", "1");

    // 娴嬭瘯1: 鐢熸垚token
    //String token = jwtOperator.generateToken(objectObjectHashMap);
    //// 浼氱敓鎴愮被浼艰瀛楃涓茬殑鍐呭: eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE1NjU1ODk4MTcsImV4cCI6MTU2Njc5OTQxN30.27_QgdtTg4SUgxidW6ALHFsZPgMtjCQ4ZYTRmZroKCQ
    //System.out.println(token);

    // 灏嗚繖涓插瓧绗﹁繕鍘熶负涓婇潰鐢熸垚鐨則oken!!!
    //String someToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE2MDI1NjkzNzQsImV4cCI6MTYwMzc3ODk3NH0.filbMWYqwMfR5fLOJwm5nb8MrRjpTzqIkuHupsUMvGE";
    //// 娴嬭瘯2: 濡傛灉鑳絫oken鍚堟硶涓旀湭杩囨湡锛岃繑鍥瀟rue
    //Boolean validateToken = jwtOperator.validateToken(someToken);
    //System.out.println(validateToken);
    ////
    ////// 娴嬭瘯3: 鑾峰彇鐢ㄦ埛淇℃伅
    //Claims claims = jwtOperator.getClaimsFromToken(someToken);
    //System.out.println(claims);
    //
    //// token鐨勭涓€娈碉紙浠�.涓鸿竟鐣岋級
    //String encodedHeader = "eyJhbGciOiJIUzI1NiJ9";
    //// 娴嬭瘯4: 瑙ｅ瘑Header
    //byte[] header = Base64.decodeBase64(encodedHeader.getBytes());
    //System.out.println(new String(header));
    //
    //// token鐨勭浜屾锛堜互.涓鸿竟鐣岋級
    //String encodedPayload = "eyJpZCI6IjEiLCJpYXQiOjE1NjU1ODk1NDEsImV4cCI6MTU2Njc5OTE0MX0";
    //// 娴嬭瘯5: 瑙ｅ瘑Payload
    //byte[] payload = Base64.decodeBase64(encodedPayload.getBytes());
    //System.out.println(new String(payload));
    //
    //// 娴嬭瘯6: 杩欐槸涓€涓绡℃敼鐨則oken锛屽洜姝や細鎶ュ紓甯革紝璇存槑JWT鏄畨鍏ㄧ殑
    //boolean flag = jwtOperator.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE2MDI1NjkzNzQsImV4cCI6MTYwMzc3ODk3NH0.filbMWYqwMfR5fLddwm5nb8MrRjpTzq444uHupsUMvGE");
    //}
}
