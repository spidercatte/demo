package com.example.demo.db1.repository;

import jakarta.persistence.Tuple;

import com.example.demo.db1.entity.Wager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface WagerRepository extends JpaRepository<Wager, UUID> {


    @Query("SELECT b.accountId, b.date, b.total FROM " +
            "(SELECT e.accountId AS accountId, DATE(e.wagerTime) AS date, SUM(e.wagerAmount) AS total " +
            "FROM Wager e GROUP BY e.accountId, DATE(e.wagerTime)) AS b " +
            "WHERE (:accountId IS NULL OR b.accountId = :accountId) " +
            "AND (:date IS NULL OR DATE(b.date) = :date)")
    List<Tuple> getSummary(@Param("accountId") UUID accountId, @Param("date") Date date);
}
