package com.example.springjpatest.it;

import com.example.springjpatest.SpringJpaTestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = SpringJpaTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(value = {"production", "test"})
class SpringJpaTestApplicationTests {

    @Test
    void contextLoads() {
    }

}
