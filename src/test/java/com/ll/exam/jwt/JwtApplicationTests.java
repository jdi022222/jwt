package com.ll.exam.jwt;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;

import java.util.Base64;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class JwtApplicationTests {

    @Value("${custom.jwt.secretKey}")
    private String secretKeyPlain;

    @Test
    @DisplayName("secretKey가 존재해야 한다")
    void t1() {
        assertThat(secretKeyPlain).isNotNull();
    }

    @Test
    @DisplayName("secretKey 원문으로 SecretKey 객체를 만들 수 있어야 한다")
    void t2() {
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
        assertThat(secretKey).isNotNull();
    }
}
