package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Tracks;

import java.util.List;

public interface TrackManager {

    Tracks getTracks(List<Proposal> proposals);
}
