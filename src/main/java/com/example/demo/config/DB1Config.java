package com.example.demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.db1.repository")
@EntityScan(basePackages = "com.example.demo.db1.entity")
@PropertySource("classpath:application-db1.properties")
public class DB1Config {



}

