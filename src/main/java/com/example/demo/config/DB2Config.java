package com.example.demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.db2.repository")
@EntityScan(basePackages = "com.example.demo.db2.entity")
@PropertySource("classpath:application-db2.properties")
public class DB2Config {



}

