package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Tracks;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackManagerImplTest {

    private List<Proposal> proposals;
    private final TrackManager manager = new TrackManagerImpl();

    @Before
    public void setUp() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:test-input.txt");
        DataPreprocessor preprocessor = new DataPreProcessorImpl();
        proposals = preprocessor.loadProposals(file);

    }

    @Test
    public void should_schedule_all_available_proposals() {
        Tracks tracks = manager.getTracks(proposals);
        assertThat(tracks.getTracks()).hasSize(2);
        assertThat(tracks.getTracks().get(0).getSession()).hasSize(12);
        assertThat(tracks.getTracks().get(1).getSession()).hasSize(11);
    }

    @Test
    public void should_track_number_should_be_in_incremental_order() {
        Tracks tracks = manager.getTracks(proposals);
        assertThat(tracks.getTracks()).hasSize(2);
        assertThat(tracks.getTracks().get(0).getTrackCode()).isEqualTo("Track_1");
        assertThat(tracks.getTracks().get(1).getTrackCode()).isEqualTo("Track_2");
    }
}
