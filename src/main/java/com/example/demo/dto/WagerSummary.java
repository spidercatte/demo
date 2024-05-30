package com.example.demo.dto;

import jakarta.persistence.Tuple;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class WagerSummary {

    UUID accountId;

    BigDecimal totalWagerAmount;

    LocalDate wagerDate;


    public static WagerSummary toDto(Tuple tuple){
        UUID accountId = tuple.get(0, UUID.class);
        java.sql.Date wagerDate = tuple.get(1, java.sql.Date.class);
        BigDecimal totalWagerAmount = tuple.get(2, BigDecimal.class);

        WagerSummary wagerSummary = new WagerSummary();
        wagerSummary.accountId = accountId;
        wagerSummary.totalWagerAmount = totalWagerAmount;
        wagerSummary.wagerDate = wagerDate.toLocalDate();
        return wagerSummary;
    }

    public static com.example.demo.db2.entity.WagerSummary toEntity(WagerSummary dto){
        com.example.demo.db2.entity.WagerSummary data =  new com.example.demo.db2.entity.WagerSummary();
        data.setAccountId(dto.accountId);
        data.setWagerDate(java.sql.Date.valueOf(dto.wagerDate));
        data.setTotalWagerAmount(dto.totalWagerAmount);

        return data;
    }

}