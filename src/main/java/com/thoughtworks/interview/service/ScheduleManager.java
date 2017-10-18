package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Session;

import java.time.LocalTime;
import java.util.List;

public interface ScheduleManager {

   String LUNCH = "Lunch";
   String NETWORKING = "Networking";

   List<Session> sessions(List<Proposal> proposals);

}
