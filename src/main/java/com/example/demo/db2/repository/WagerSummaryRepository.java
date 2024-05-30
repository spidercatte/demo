package com.example.demo.db2.repository;


import com.example.demo.db2.entity.WagerSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WagerSummaryRepository extends JpaRepository<WagerSummary, UUID> {
}
