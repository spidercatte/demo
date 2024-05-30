package com.example.demo.db1.repository;

import com.example.demo.db1.entity.Wager;
import jakarta.persistence.Tuple;
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
            "(SELECT e.accountId AS accountId, DATE(e.wagerTime) AS date, SUM(e.wagerAmount) as total " +
            "FROM Wager e GROUP BY e.accountId, DATE(e.wagerTime)) AS b " +
            "WHERE DATE(b.date) = :date")
    List<Tuple> getSummary(@Param("date") Date date);
}
