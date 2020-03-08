package io.ojw.mall.user.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    private final String secretKey = "mall";

    public String createToken(String id, String name, String auth) {
        // 
    	Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + (1000 * 60 * 1));
        
        // 
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 
        Map<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        // 
        Map<String, Object> bodyMap= new HashMap<String, Object>();
        bodyMap.put("id", id);
        bodyMap.put("name", name);
        bodyMap.put("auth", auth);

        JwtBuilder builder = Jwts.builder()
					        		.setExpiration(expireTime)
					        		.signWith(signatureAlgorithm, signingKey)
					        		.setHeader(headerMap)
        							.setClaims(bodyMap)
        							;

        return builder.compact();
    }

    public boolean checkToken(String token) throws Exception {
        try {
        	// 정상 수행된다면 해당 토큰은 정상토큰
            @SuppressWarnings("unused")
			Claims claims = Jwts.parser()
            				.setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
            				.parseClaimsJws(token)
            				.getBody();
            
//            System.out.println("id :" + claims.get("id"));
//            System.out.println("auth :" + claims.get("auth"));

            return true;
        } catch (ExpiredJwtException exception) {
//            logger.info("토큰 만료");
            return false;
        } catch (JwtException exception) {
//            logger.info("토큰 변조");
            return false;
        }
    }
    
    public Claims getClaims(String token) {
		Claims claims = Jwts.parser()
							.setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
							.parseClaimsJws(token)
							.getBody();
    	return claims;
    }
    
}


































