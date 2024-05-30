package com.example.demo.controller;

import com.example.demo.dto.WagerSummary;
import com.example.demo.db1.entity.Wager;
import com.example.demo.service.ETLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ETLController {

    @Autowired
    private ETLService etlService;

    @GetMapping("/wagers")
    public List<Wager> getAllWagers() {
        return etlService.getWagers();
    }

    @GetMapping("/wagers/summary")
    public List<WagerSummary> getWagerSummary() {
        return etlService.getWagerSummary();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
