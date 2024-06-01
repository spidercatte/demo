package com.example.demo.service;

import com.example.demo.db1.repository.WagerRepository;
import com.example.demo.db2.repository.WagerSummaryRepository;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ETLServiceUnitTest {

    @Mock
    private WagerRepository wagerRepository;

    @Mock
    private WagerSummaryRepository wagerSummaryRepository;

    @InjectMocks
    private ETLService etlService;

    @BeforeAll
    public static void beforeAll() {}

    @Test
    public void test_getWagerSummary_success() {

        UUID accountId = UUID.randomUUID();
        java.sql.Date wagerDate = java.sql.Date.valueOf("2024-05-29");
        BigDecimal totalWagerAmount = BigDecimal.valueOf(1000);

        List<Tuple> tuples = new ArrayList<>();
        tuples.add(mockTuple(accountId, wagerDate, totalWagerAmount));

        List<com.example.demo.db2.entity.WagerSummary> expected = new ArrayList<>();
        expected.add(createResult(accountId, wagerDate, totalWagerAmount));

        when(wagerRepository.getSummary(accountId, wagerDate)).thenReturn(tuples);
        when(wagerSummaryRepository
                .saveAll(expected)).thenReturn(expected);

        List<com.example.demo.db2.entity.WagerSummary> result = etlService.getWagerSummary(accountId, wagerDate.toLocalDate());

        verify(wagerRepository, atLeastOnce()).getSummary(accountId, wagerDate);
        verify(wagerSummaryRepository, atLeastOnce()).saveAll(anyList());

        Assertions.assertEquals(tuples.size(), result.size());
        Assertions.assertEquals(result.get(0).getAccountId(), accountId);
        Assertions.assertEquals(result.get(0).getWagerDate(), wagerDate);
        Assertions.assertEquals(result.get(0).getTotalWagerAmount(), BigDecimal.valueOf(1000));

    }

    @Test
    public void test_getWagerSummary_null_params() {
        List<com.example.demo.db2.entity.WagerSummary> result = etlService.getWagerSummary(null, null);

        verify(wagerSummaryRepository, never()).saveAll(anyList());
        Assertions.assertEquals(0, result.size()); //which is zero
    }

    @Test
    public void test_getWagerSummary_no_return_wagers() {

        UUID accountId = UUID.randomUUID();
        java.sql.Date wagerDate = java.sql.Date.valueOf("2024-05-29");
        List<Tuple> tuples = new ArrayList<>();
        List<com.example.demo.db2.entity.WagerSummary> result = etlService.getWagerSummary(accountId, wagerDate.toLocalDate());

        verify(wagerRepository, atLeastOnce()).getSummary(accountId, wagerDate);
        verify(wagerSummaryRepository, never()).saveAll(anyList());
        Assertions.assertEquals(tuples.size(), result.size()); //which is zero
    }

    @Test
    public void test_getWagerSummary_except_on_get_summary_call() {

        UUID accountId = UUID.randomUUID();
        java.sql.Date wagerDate = java.sql.Date.valueOf("2024-05-29");

        doThrow(new RuntimeException("cannot connect to the db"))
                .when(wagerRepository).getSummary(accountId, wagerDate);

        try {
            List<com.example.demo.db2.entity.WagerSummary> result = etlService.getWagerSummary(accountId, wagerDate.toLocalDate());
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), RuntimeException.class);
            Assertions.assertEquals(e.getMessage(), "cannot connect to the db");
        }

        verify(wagerRepository, atLeastOnce()).getSummary(accountId, wagerDate);
        verify(wagerSummaryRepository, never()).saveAll(anyList());

    }

    @Test
    public void test_getWagerSummary_exception_on_save() {

        UUID accountId = UUID.randomUUID();
        java.sql.Date wagerDate = java.sql.Date.valueOf("2024-05-29");
        BigDecimal totalWagerAmount = BigDecimal.valueOf(1000);

        List<Tuple> tuples = new ArrayList<>();
        tuples.add(mockTuple(accountId, wagerDate, totalWagerAmount));

        List<com.example.demo.db2.entity.WagerSummary> expected = new ArrayList<>();
        expected.add(createResult(accountId, wagerDate, totalWagerAmount));

        when(wagerRepository.getSummary(accountId, wagerDate)).thenReturn(tuples);
        doThrow(new RuntimeException("cannot connect to the db"))
                .when(wagerSummaryRepository).saveAll(expected);

        try {
            List<com.example.demo.db2.entity.WagerSummary> result = etlService.getWagerSummary(accountId, wagerDate.toLocalDate());
        } catch (Exception e) {
            Assertions.assertEquals(e.getClass(), RuntimeException.class);
            Assertions.assertEquals(e.getMessage(), "cannot connect to the db");
        }
        verify(wagerRepository, atLeastOnce()).getSummary(accountId, wagerDate);
        verify(wagerSummaryRepository, atLeastOnce()).saveAll(anyList());

    }


    private Tuple mockTuple(UUID accountId, java.sql.Date wagerDate , BigDecimal totalWagerAmount) {
        Tuple tuple = Mockito.mock(Tuple.class);
        when(tuple.get(0, UUID.class)).thenReturn(accountId);
        when(tuple.get(1, java.sql.Date.class)).thenReturn(wagerDate);
        when(tuple.get(2, BigDecimal.class)).thenReturn(totalWagerAmount);
        return tuple;
    }

    private com.example.demo.db2.entity.WagerSummary createResult(UUID accountId, java.sql.Date wagerDate , BigDecimal totalWagerAmount) {
        com.example.demo.db2.entity.WagerSummary item = new com.example.demo.db2.entity.WagerSummary();
        item.setAccountId(accountId);
        item.setWagerDate(wagerDate);
        item.setTotalWagerAmount(totalWagerAmount);
        return item;
    }


}
