package com.example.demo.service;

import com.example.demo.db2.repository.WagerSummaryRepository;
import com.example.demo.dto.WagerSummary;
import com.example.demo.db1.entity.Wager;
import com.example.demo.db1.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Tuple;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ETLService {

    @Autowired
    WagerRepository wagerRepository;

    @Autowired
    WagerSummaryRepository wagerSummaryRepository;

    public List<Wager> getWagers() {
        return wagerRepository.findAll();
    }

    @Transactional
    public List<WagerSummary> getWagerSummary(LocalDate searchDate) {
        List<Tuple> tuples = wagerRepository.getSummary(java.sql.Date.valueOf(searchDate));

        List<WagerSummary> summary = new ArrayList<>();
        List<com.example.demo.db2.entity.WagerSummary> summaryData = new ArrayList<>();

        for (Tuple tuple : tuples) {
            WagerSummary dto = WagerSummary.toDto(tuple);
            com.example.demo.db2.entity.WagerSummary data = WagerSummary.toEntity(dto);
            summaryData.add(data);
            summary.add(dto);
        }

        wagerSummaryRepository.saveAll(summaryData);

        return summary;
    }


}
