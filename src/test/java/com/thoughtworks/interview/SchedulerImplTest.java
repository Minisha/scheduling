package com.thoughtworks.interview;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.service.DataPreProcessorImpl;
import com.thoughtworks.interview.service.DataPreprocessor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
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
        final LocalTime startTime = LocalTime.of(9, 0);
        final LocalTime endTime = LocalTime.of(12, 0);



    }
}
