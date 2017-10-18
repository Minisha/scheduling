package com.thoughtworks.interview;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.service.DataPreProcessorImpl;
import com.thoughtworks.interview.service.DataPreprocessor;
import com.thoughtworks.interview.service.Scheduler;
import com.thoughtworks.interview.service.SchedulerImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

public class SchedulerImplTest {

    List<Proposal> proposals;

    @Before
    public void setUp() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:test-input.txt");
        DataPreprocessor preprocessor = new DataPreProcessorImpl();
        proposals = preprocessor.loadProposals(file);

    }

    @Test
    public void test_time() {
        final Instant startTime = Instant.ofEpochSecond(LocalTime.of(9, 0).getSecond());
        final Instant endTime = Instant.ofEpochSecond(LocalTime.of(12, 0).getSecond());

        SchedulerImpl scheduler = new SchedulerImpl();
        scheduler.getSessionSchedule(startTime, endTime, proposals);

    }
}
