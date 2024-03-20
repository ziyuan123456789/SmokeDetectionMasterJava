package com.example.SmokeDetectionMaster.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @author ziyuan
 * @since 2024.03
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void addToBlacklist(String jwtId, long expirationTime) {
        redisTemplate.opsForValue().set(jwtId, "blacklisted", expirationTime, TimeUnit.MILLISECONDS);
    }

    public boolean isBlacklisted(String jwtId) {
        return redisTemplate.hasKey(jwtId);
    }
}
