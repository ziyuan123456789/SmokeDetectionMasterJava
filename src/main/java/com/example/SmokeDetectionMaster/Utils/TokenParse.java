package com.example.SmokeDetectionMaster.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.HashMap;
import java.util.Map;
/**
 * @author wsh
 */
public class TokenParse {
    public static Map<String,String> getToken(String token, String[] payloadNames, String hs512Key) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(hs512Key)
                    .build()
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            Map<String,String> resultMap = new HashMap<>();
            for (String payloadName : payloadNames) {
                resultMap.put(payloadName, (String) claims.get(payloadName));
            }
            return resultMap;
        }catch (Exception e) {
            return null;
        }
    }

}
