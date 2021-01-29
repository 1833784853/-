package com.imcode.common.model;

import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.logging.Logger;

public class JwtUtils {
    public static final String ISS_USER = "USER";
    public static final long TTLMILLIS = 1000 * 60 * 60 * 24; // 一天后令牌失效
    //密钥
    public static final String SECRET = "sdjhakdhajdklslo653632";

    /**
     * 签发JWT
     *
     * @param id
     * @param subject   可以是JSON数据 尽可能少
     * @param ttlMillis
     * @return String
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject) // 主体
                .setIssuer(ISS_USER) // 签发者
                .setIssuedAt(now) // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }


    /**
     * 验证JWT
     *
     * @param jwtStr
     * @return
     */
    public static boolean validateJWT(String jwtStr) {
        Claims claims = null;
        try {
            Claims jw = parseJWT(jwtStr);
            if (jw.get("sub") != null) {
                return true;
            }
        } catch (ExpiredJwtException e) {
            Logger.getLogger(e.getMessage());
        } catch (SignatureException e) {
            Logger.getLogger(e.getMessage());
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
        return false;
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(SECRET);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }
}
