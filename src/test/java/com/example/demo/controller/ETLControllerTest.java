package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ETLControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_getSummaries_success() throws Exception {
        String date = "2024-05-29";
        ClassPathResource resource = new ClassPathResource("expected.json");
        String expectedJson = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        mvc.perform(get("/api/wagers/summary")
                .param("date", date))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    public void test_hello_success() throws Exception {
        mvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));

    }

}
