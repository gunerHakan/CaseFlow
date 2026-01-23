package com.law.caseflow.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                // 1. GENEL AYAR: Varsayılan olarak her şey 10 dakika (veya istediğin süre) kalsın
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(10)))

                // 2. ÖZEL AYARLAR: İstersen belirli cache isimleri için süreyi değiştirebilirsin
                // Mesela "all_cases" listesi çok sık değişiyorsa ömrü sadece 1 dakika olsun
                .withCacheConfiguration("all_cases",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(1)))

                // "cases" (tekil dosyalar) 20 dakika kalsın
                .withCacheConfiguration("cases",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(20)));
    }
}