package com.example.demo.controller;

import com.example.demo.dto.WagerSummary;
import com.example.demo.service.ETLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ETLController {

    @Autowired
    private ETLService etlService;

    @GetMapping("/wagers/summary")
    public List<WagerSummary> getWagerSummary(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return etlService.getWagerSummary(date);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
