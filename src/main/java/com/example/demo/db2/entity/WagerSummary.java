package com.example.demo.db2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@Data
public class WagerSummary {

    @Id
    UUID accountId;

    BigDecimal totalWagerAmount;

    Date wagerDate;

}
