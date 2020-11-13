package com.linflix.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
public class single {
    public static void main(String[] args) {
        operSingle();
    }

    private static void operSingle() {
        //1.redisUri:链接信息
        RedisURI redisURI = RedisURI.builder()
                .withHost("47.96.154.52")
                .withPort(6379)
                .withPassword("agan")
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient client =
    }
}
