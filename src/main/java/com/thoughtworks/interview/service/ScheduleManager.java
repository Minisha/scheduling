package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Session;

import java.util.List;

public interface ScheduleManager {

    List<Session> sessions(List<Proposal> proposals);

}
