package com.example.demo.service;

import com.example.demo.db1.repository.WagerRepository;
import com.example.demo.db2.repository.WagerSummaryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ETLServiceTest {

    @Mock
    private WagerRepository wagerRepository;

    @Mock
    private WagerSummaryRepository wagerSummaryRepository;

    @InjectMocks
    private ETLService etlService;

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(ETLServiceTest.class);
    }


}
