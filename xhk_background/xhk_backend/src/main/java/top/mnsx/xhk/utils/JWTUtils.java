package top.mnsx.xhk.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * JWT工具类
 */
public class JWTUtils {
    /**
     * 生成token
     * @param map payload需要挂载的数据
     * @param code 密钥
     * @return 返回token
     */
    public static String createToken(Map<String, String> map, String code) {
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 60 * 60 * 24);
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(code)).toString();
    }

    /**
     * 验证token
     * @param token token字符串
     * @param code 密钥
     */
    public static void verify(String token, String code) {
        JWT.require(Algorithm.HMAC256(code)).build().verify(token);
    }

    /**
     * 获取token中的挂载数据
     * @param token token字符串
     * @param code 密钥
     * @return token中挂载的数据
     */
    public static DecodedJWT getToken(String token, String code) {
        return JWT.require(Algorithm.HMAC256(code)).build().verify(token);
    }
}
