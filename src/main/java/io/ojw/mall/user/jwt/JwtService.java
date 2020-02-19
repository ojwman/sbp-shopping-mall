package io.ojw.mall.user.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
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

    private String secretKey = "mall";

    public String makeJwt(String id, String auth) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 1);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String, Object> map= new HashMap<String, Object>();

//        String name = res.getParameter("name");
//        String email = res.getParameter("email");

//        map.put("name", name);
//        map.put("email", email);

        map.put("id", id);
        map.put("auth", auth);
        
        

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expireTime)
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public boolean checkJwt(String jwt) throws Exception {
        try {
            Claims claims = Jwts.parser()
            				.setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
            				.parseClaimsJws(jwt)
            				.getBody(); // 정상 수행된다면 해당 토큰은 정상토큰

//            logger.info("expireTime :" + claims.getExpiration());
//            logger.info("name :" + claims.get("name"));
//            logger.info("Email :" + claims.get("email"));
            
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
}