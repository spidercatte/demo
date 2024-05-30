package com.example.demo.db1.repository;

import com.example.demo.dto.WagerSummary;
import com.example.demo.db1.entity.Wager;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WagerRepository extends JpaRepository<Wager, UUID> {

    @Query("SELECT e.accountId, DATE(e.wagerTime), SUM(e.wagerAmount) FROM Wager e GROUP BY e.accountId, DATE(e.wagerTime)")
    List<Tuple> getSummary();
}
