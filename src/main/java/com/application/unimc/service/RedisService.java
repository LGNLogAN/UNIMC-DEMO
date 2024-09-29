package com.application.unimc.service;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	@Autowired
    private RedisTemplate<String, String> redisTemplate;

    // 인증 코드를 Redis에 저장하는 메소드 (5분 동안 저장)
    public void setVerificationCode(String email, String verificationCode) {
        redisTemplate.opsForValue().set(email, verificationCode, 5, TimeUnit.MINUTES);
        printAllData();
    }

    // 인증 코드를 Redis에서 조회하는 메소드
    public String getVerificationCode(String email) {
        return redisTemplate.opsForValue().get(email);
    }
    
    // 내부 데이터 호출
    public void printAllData() {
        Set<String> keys = redisTemplate.keys("*");

        if (keys != null) {
            for (String key : keys) {
                // 각 키에 대한 값을 가져와 출력
                String value = redisTemplate.opsForValue().get(key);
                Long expireTime = redisTemplate.getExpire(key, TimeUnit.SECONDS); // 유효시간(초 단위) 조회

                if (expireTime != null && expireTime > 0) {
                    System.out.println("Key: " + key + ", Value: " + value + ", TTL: " + expireTime + " seconds remaining");
                } else {
                    System.out.println("Key: " + key + ", Value: " + value + " (no expiration time set or expired)");
                }
            }
        } else {
            System.out.println("No data found in Redis.");
        }
    }
}
