package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;

import java.io.File;
import java.util.List;

public interface DataPreprocessor {

    List<Proposal> loadProposals(File file);
}
