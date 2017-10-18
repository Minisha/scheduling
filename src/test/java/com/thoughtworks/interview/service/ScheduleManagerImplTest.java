package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Session;
import com.thoughtworks.interview.service.DataPreProcessorImpl;
import com.thoughtworks.interview.service.DataPreprocessor;
import com.thoughtworks.interview.service.ScheduleManager;
import com.thoughtworks.interview.service.ScheduleManagerImpl;
import com.thoughtworks.interview.util.ApplicationUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static com.thoughtworks.interview.service.ScheduleManager.LUNCH;
import static com.thoughtworks.interview.service.ScheduleManager.NETWORKING;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ScheduleManagerImplTest {

    private List<Proposal> proposals;
    private final ScheduleManager manager = new ScheduleManagerImpl();

    @Before
    public void setUp() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:test-input.txt");
        DataPreprocessor preprocessor = new DataPreProcessorImpl();
        proposals = preprocessor.loadProposals(file);

    }

    @Test
    public void should_return_session_title_and_startTime() {
        List<Session> sessions = manager.sessions(proposals);
        sessions.stream().forEach(s -> {
            assertThat(s.getTitle()).isNotEmpty();
            assertThat(s.getStartTime()).isNotEmpty();
        });
    }

    @Test
    public void should_include_lunch_schedule_at_12PM() {
        List<Session> sessions = manager.sessions(proposals);

        Session session = sessions.stream().filter(s -> s.getTitle().equals(LUNCH)).findFirst().get();
        assertThat(session.getStartTime()).isEqualToIgnoringCase("12:00PM");
    }

    @Test
    public void should_include_networking_schedule_not_earlier_than_4PM() {
        List<Session> sessions = manager.sessions(proposals);

        Session session = sessions.stream().filter(s -> s.getTitle().equals(NETWORKING)).findFirst().get();
        assertThat(session.getStartTime()).isEqualToIgnoringCase("04:35PM");
    }

    @Test
    public void should_include_networking_schedule_in_next_available_slot_after_4PM() {
        List<Session> session1 = manager.sessions(proposals);
        List<Session> session2 = manager.sessions(ApplicationUtil.getListDifference(session1, proposals));
        Session nwtInsession2 = session2.stream().filter(s -> s.getTitle().equals(NETWORKING)).findFirst().get();
        assertThat(nwtInsession2.getStartTime()).isEqualToIgnoringCase("04:45PM");
    }

}
