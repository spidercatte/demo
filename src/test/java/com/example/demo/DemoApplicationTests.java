package com.example.demo;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;



@SpringBootTest
@Testcontainers
class DemoApplicationTests {

    @Container
    private static final MySQLContainer<?> db1Container = new MySQLContainer<>("mysql:8.0.23")
            .withUsername("test")
            .withPassword("")
            .withDatabaseName("db1");


    @Container
    private static final MySQLContainer<?> db2Container = new MySQLContainer<>("mysql:8.0.23")
            .withUsername("test")
            .withPassword("")
            .withDatabaseName("db2");

    @Test
    void contextLoads() {
        //db1Container.start();
        //db2Container.start();
    }

}
