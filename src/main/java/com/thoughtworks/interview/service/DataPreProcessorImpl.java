package com.thoughtworks.interview.service;

import com.thoughtworks.interview.ApplicationMain;
import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.ProposalType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataPreProcessorImpl implements DataPreprocessor {

    private static final Logger logger = LoggerFactory.getLogger(DataPreProcessorImpl.class);

    public static final String MIN = "min";

    @Override
    public List<Proposal> loadProposals(File file) {
        List<Proposal> proposals;
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            proposals = stream.map(l -> proposal(l)).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return proposals;
    }

    private Proposal proposal(String dataPoint) {

        String title = dataPoint;
        Instant duration = null;

        Optional<ProposalType> type = ProposalType.type(dataPoint);
        if (type.isPresent()) {
            duration = type.get().getDuration();
        } else if(dataPoint.contains(MIN)) {
            title = dataPoint.substring(0, dataPoint.lastIndexOf(" "));
            Integer time = Integer.parseInt(dataPoint.substring(dataPoint.lastIndexOf(" ") + 1).replaceAll(MIN, ""));
            duration = Instant.ofEpochSecond(time*60);
        } else {
            logger.info("Invalid data {}", dataPoint);
        }

        return Proposal.builder().title(title).duration(duration).build();
    }

}
