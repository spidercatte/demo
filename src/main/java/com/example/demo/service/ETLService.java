package com.example.demo.service;

import com.example.demo.dto.WagerSummary;
import com.example.demo.db1.entity.Wager;
import com.example.demo.db1.repository.WagerRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ETLService {

    @Autowired
    WagerRepository wagerRepository;

    public List<Wager> getWagers() {
        return wagerRepository.findAll();
    }

    public List<WagerSummary> getWagerSummary() {
        List<Tuple> tuples = wagerRepository.getSummary();
        List<WagerSummary> summary = new ArrayList<>();
        for (Tuple tuple : tuples) {
            UUID accountId = tuple.get(0, UUID.class);
            Date date = tuple.get(1, Date.class);
            BigDecimal totalWagerAmount = tuple.get(2, BigDecimal.class);
            WagerSummary wagerSummary = WagerSummary.builder(accountId,totalWagerAmount,date);
            summary.add(wagerSummary);
        }
        return summary;
    }


}
