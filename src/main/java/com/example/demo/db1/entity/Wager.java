package com.example.demo.db1.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="wager")
@Data
public class Wager {

    @Id
    @Column(name = "account_id")
    UUID accountId;

    @Column(name = "wager_amount")
    BigDecimal wagerAmount;

    @Column(name = "wager_time")
    Timestamp wagerTime;

}
