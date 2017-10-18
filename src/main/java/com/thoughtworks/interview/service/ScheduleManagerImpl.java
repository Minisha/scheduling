package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Session;
import com.thoughtworks.interview.util.ApplicationUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

public class ScheduleManagerImpl implements ScheduleManager {

    public  final String LUNCH = "Lunch";
    public  final String NETWORKING = "Networking";
    private final LocalTime morningStartTime = LocalTime.of(9, 0);
    private final LocalTime morningEndTime = LocalTime.of(12, 0);

    private final LocalTime lunchTime = LocalTime.of(12, 0);

    private final LocalTime noonStartTime = LocalTime.of(13, 0);
    private final LocalTime noonEndTime = LocalTime.of(17, 0);

    private final LocalTime networkingStartTime = LocalTime.of(16, 0);


    public List<Session> sessions(List<Proposal> proposals) {
        List<Session> morningSessions = getMorningSessions(proposals);
        morningSessions.add(Session.builder().title(LUNCH).startTime(ApplicationUtil.time(lunchTime)).duration(60).build());
        List<Session> noonSessions = getNoonSessions(ApplicationUtil.getListDifference(morningSessions, proposals));

        Session lastSessionStartTime = noonSessions.get(noonSessions.size() - 1);
        LocalTime lastSessionEndTime = ApplicationUtil.dateStringToLocalTime(lastSessionStartTime.getStartTime()).plusMinutes(lastSessionStartTime.getDuration());

        if(lastSessionEndTime.isBefore(noonEndTime) && lastSessionEndTime.isAfter(networkingStartTime))  {
            noonSessions.add(Session.builder().title(NETWORKING).startTime(ApplicationUtil.time(lastSessionEndTime)).build());
        } else {
            noonSessions.add(Session.builder().title(NETWORKING).startTime(ApplicationUtil.time(networkingStartTime)).build());
        }
        morningSessions.addAll(noonSessions);
        return morningSessions;
    }


    private List<Session> getMorningSessions(List<Proposal> proposals) {
        return schedule(morningStartTime, morningEndTime, proposals);
    }


    private List<Session> getNoonSessions(List<Proposal> proposals) {
        return schedule(noonStartTime, noonEndTime, proposals);
    }


    private List<Session> schedule(LocalTime startTime, LocalTime endTime, List<Proposal> proposals) {

        long availableTime = MINUTES.between(startTime, endTime);

        Map<String, Session> sessions = new HashMap<>();
        long remainingDuration = availableTime;
        LocalTime sessionStartTime  = startTime;

        for(Proposal p : proposals) {
            if (sessions.get(p.getTitle()) == null && remainingDuration > 0 && p.getDuration() <= remainingDuration) {
                sessions.put(p.getTitle(), Session.builder()
                        .title(p.getTitle())
                        .duration(p.getDuration())
                        .startTime(ApplicationUtil.time(sessionStartTime))
                        .build());
                sessionStartTime = sessionStartTime.plusMinutes(p.getDuration());
                remainingDuration = remainingDuration - p.getDuration();
            }
        }

        return new ArrayList(sessions.values());
    }
}
