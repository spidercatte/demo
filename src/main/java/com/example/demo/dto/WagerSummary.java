package com.example.demo.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
public class WagerSummary {

    @Id
    UUID accountId;

    BigDecimal totalWagerAmount;

    Date wagerDate;


    public static WagerSummary builder(UUID accountId, BigDecimal totalWagerAmount, Date wagerDate){
        WagerSummary wagerSummary = new WagerSummary();
        wagerSummary.accountId = accountId;
        wagerSummary.totalWagerAmount = totalWagerAmount;
        wagerSummary.wagerDate = wagerDate;
        return wagerSummary;
    }

}