package com.thoughtworks.interview;

import com.thoughtworks.interview.config.ApplicationConfig;
import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.ProposalType;
import io.advantageous.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ApplicationMain {

    // 9am and must finish by 12 noon
    //1pm and 4.30, 4.45 pm

    private static final Logger logger = LoggerFactory.getLogger(ApplicationMain.class);
    public static final String MIN = "min";

    public static void main(String[] args) {
        ApplicationMain main = new ApplicationMain();
        List<Proposal> proposals = main.loadProposals();
        proposals.forEach(l -> System.out.println(l.getTitle() + " = " +l.getDuration()));
    }

    private List<Proposal> loadProposals() {
        List<Proposal> proposals;
        try (Stream<String> stream = Files.lines(Paths.get(loadInputFile().getAbsolutePath()))) {
            proposals = stream.map(l -> proposal(l)).collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return proposals;
    }


    private Proposal proposal(String dataPoint) {

        String title = dataPoint;
        String duration = "";

        Optional<ProposalType> type = ProposalType.type(dataPoint);
        if (type.isPresent()) {
            duration = type.get().getDuration();
        } else if(dataPoint.contains(MIN)) {
            title = dataPoint.substring(0, dataPoint.lastIndexOf(" "));
            duration = dataPoint.substring(dataPoint.lastIndexOf(" ") + 1);
        } else {
            logger.info("Invalid data {}", dataPoint);
        }

        return Proposal.builder().title(title).duration(duration).build();
    }

    private File loadInputFile() {
        try {
            Config config = ApplicationConfig.config();
            String inputFile = config.getString("application.file");
            return ResourceUtils.getFile(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
