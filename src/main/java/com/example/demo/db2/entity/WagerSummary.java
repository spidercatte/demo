package com.example.demo.db2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name="wager_summary")
@Data
public class WagerSummary {

    @Id
    @Column(name = "account_id")
    UUID accountId;

    @Column(name = "total_wager_amount")
    BigDecimal totalWagerAmount;

    @Column(name = "wager_date")
    Date wagerDate;

}
