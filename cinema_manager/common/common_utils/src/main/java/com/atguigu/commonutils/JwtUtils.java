package com.atguigu.commonutils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author helen
 * @since 2019/10/16
 */
public class JwtUtils {

    //常量
    private static long tokenExpiration = 1000 * 60 * 60 * 24;
    private static String tokenSignKey = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";


    //创建token
    public static String createToken(String userId) {
        String token = Jwts.builder().setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.HS256, tokenSignKey).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    public static String getUserIdFromToken(String token) {
        String userId = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return userId;
    }


    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }
    /**
     * 判断token是否存在与有效
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
