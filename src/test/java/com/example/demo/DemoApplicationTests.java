package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;



@SpringBootTest
@Profile("test")
class DemoApplicationTests {


    @Test
    void contextLoads() {
    }

}
