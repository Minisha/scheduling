package com.thoughtworks.interview;

import com.thoughtworks.interview.config.ApplicationConfig;
import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Tracks;
import com.thoughtworks.interview.service.*;
import io.advantageous.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


public class ApplicationMain {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) {
        DataPreprocessor main = new DataPreProcessorImpl();
        List<Proposal> proposals = main.loadProposals(loadInputFile());
        TrackManager manager = new TrackManagerImpl();
        Tracks tracks = manager.getTracks(proposals);
        tracks.getTracks().stream().forEach(t -> {
            System.out.printf(t.getTrackCode() +": \n");
            t.getSession().stream().forEach(session ->
                    System.out.printf(session.getStartTime() +" "+ session.getTitle()+"\n"));
            System.out.printf("\n");
        });
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
