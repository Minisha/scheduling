package com.thoughtworks.interview;

import com.thoughtworks.interview.config.ApplicationConfig;
import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.ProposalType;
import com.thoughtworks.interview.service.DataPreProcessorImpl;
import com.thoughtworks.interview.service.DataPreprocessor;
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

    private static final Logger logger = LoggerFactory.getLogger(ApplicationMain.class);


    public static void main(String[] args) {
        DataPreprocessor main = new DataPreProcessorImpl();
        List<Proposal> proposals = main.loadProposals(loadInputFile());
        proposals.forEach(l -> System.out.println(l.getTitle() + " = " +l.getDuration()));
    }


    private static File loadInputFile() {
        try {
            Config config = ApplicationConfig.config();
            String inputFile = config.getString("application.file");
            return ResourceUtils.getFile(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
