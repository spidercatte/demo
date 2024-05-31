package com.example.demo.controller;

import com.example.demo.service.ETLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ETLController {

    @Autowired
    private ETLService etlService;

    @GetMapping("/wagers/summary")
    public List<com.example.demo.db2.entity.WagerSummary> getWagerSummary(
            @RequestParam(name="date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam(name="accountId", required = false) UUID accountId) {

        return etlService.getWagerSummary(accountId, date);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
