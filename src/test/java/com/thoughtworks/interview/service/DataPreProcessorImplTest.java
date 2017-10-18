package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.service.DataPreProcessorImpl;
import com.thoughtworks.interview.service.DataPreprocessor;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DataPreProcessorImplTest {

    private List<Proposal> proposals;
    private final DataPreprocessor preprocessor = new DataPreProcessorImpl();

    @Test
    public void should_load_all_valid_proposals_from_file() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:test-input.txt");
        List<Proposal> proposals = preprocessor.loadProposals(file);
        assertThat(proposals).hasSize(19);
    }

    @Test
    public void should_ignore_proposals_with_special_characters() throws FileNotFoundException{
        File file = ResourceUtils.getFile("classpath:test-invalid.txt");
        List<Proposal> proposals = preprocessor.loadProposals(file);
        assertThat(proposals).hasSize(0);
    }
}
