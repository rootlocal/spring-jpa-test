package com.example.springjpatest.it;

import com.example.springjpatest.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles(value = {"production", "test"})
class ApplicationTests {

    @Test
    void contextLoads() {
    }

}
