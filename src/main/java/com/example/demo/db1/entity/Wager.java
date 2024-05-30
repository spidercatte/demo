package com.example.demo.db1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
public class Wager {

    @Id
    @Column(name = "accountId")
    UUID accountId;

    @Column(name = "wagerAmount")
    BigDecimal wagerAmount;

    @Column(name = "wagerTime")
    Timestamp wagerTime;

}
