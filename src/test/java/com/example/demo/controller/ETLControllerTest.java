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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ETLControllerTest {

    @Autowired
    private MockMvc mvc;

    String date = "2024-05-29";
    String accountId = "550e8400-e29b-41d4-a716-446655440000";

    @Test
    public void test_getSummaries_no_param_success() throws Exception {
        ClassPathResource resource = new ClassPathResource("expected_date_search.json");
        String expectedJson = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        mvc.perform(get("/api/wagers/summary"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    public void test_getSummaries_date_success() throws Exception {
        ClassPathResource resource = new ClassPathResource("expected_date_search.json");
        String expectedJson = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        mvc.perform(get("/api/wagers/summary")
                .param("date", date))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    public void test_getSummaries_accountid_success() throws Exception {
        ClassPathResource resource = new ClassPathResource("expected_account_search.json");
        String expectedJson = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        mvc.perform(get("/api/wagers/summary")
                        .param("accountId", accountId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    public void test_getSummaries_accountid_and_date_success() throws Exception {
        ClassPathResource resource = new ClassPathResource("expected_account_and_date_search.json");
        String expectedJson = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        mvc.perform(get("/api/wagers/summary")
                        .param("accountId", accountId)
                        .param("date",date))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }

    @Test
    public void test_getSummaries_invalid_date() throws Exception {
        String date = "05-29-2024";
        mvc.perform(get("/api/wagers/summary")
                        .param("date", date))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void test_getSummaries_invalid_accountid() throws Exception {
        String accountId = "550e8400-e29b-41d4-a716-446655440000x";
        mvc.perform(get("/api/wagers/summary")
                        .param("accountId", accountId))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void test_getSummaries_one_field_invalid() throws Exception {
        String accountId = "550e8400-e29b-41d4-a716-446655440000x";
        String date = "2024-05-29";
        mvc.perform(get("/api/wagers/summary")
                        .param("accountId", accountId)
                        .param("date", date))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void test_hello_success() throws Exception {
        mvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));

    }

}
