package com.example.springjpatest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = {"production", "test"})
class SpringJpaTestApplicationTests {

    @Test
    void contextLoads() {
    }

}
