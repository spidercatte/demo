package com.example.demo.service;

import com.example.demo.db2.repository.WagerSummaryRepository;
import com.example.demo.dto.WagerSummary;
import com.example.demo.db1.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Tuple;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ETLService {

    @Autowired
    WagerRepository wagerRepository;

    @Autowired
    WagerSummaryRepository wagerSummaryRepository;

    @Transactional("db1TransactionManager")
    public List<com.example.demo.db2.entity.WagerSummary> getWagerSummary(UUID accountId,LocalDate searchDate) {
        java.sql.Date sqlDate = null;
        UUID searchAccountId = null;
        if (searchDate != null) {
            sqlDate = java.sql.Date.valueOf(searchDate);
        }
        if(accountId != null) {
            searchAccountId = accountId;
        }

        List<Tuple> tuples = wagerRepository.getSummary(searchAccountId, sqlDate);
        List<com.example.demo.db2.entity.WagerSummary> summaryData = new ArrayList<>();

        for (Tuple tuple : tuples) {
            com.example.demo.db2.entity.WagerSummary data = WagerSummary.toEntity(tuple);
            summaryData.add(data);
        }

        if(!summaryData.isEmpty()) {
            wagerSummaryRepository.saveAll(summaryData);
        }

        return summaryData;
    }


}
