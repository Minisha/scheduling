package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Session;
import com.thoughtworks.interview.model.Track;
import com.thoughtworks.interview.model.Tracks;
import com.thoughtworks.interview.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

public class TrackManagerImpl implements TrackManager {

    public static final String TRACK = "Track_";

    @Override
    public Tracks getTracks(List<Proposal> proposals) {
        ScheduleManager manager = new ScheduleManagerImpl();
        List<Session> scheduledSessions = new ArrayList<>();
        List<Track> tracks = new ArrayList<>();
        int trackCount = 1;

        List<Session> sessions = manager.sessions(proposals);
        scheduledSessions.addAll(sessions);
        List<Proposal> remainingProposals = ApplicationUtil.getListDifference(sessions, proposals);
        tracks.add(Track.builder().session(sessions).trackCode(TRACK+trackCount).build());

        while (remainingProposals.size() > 0) {
            sessions = manager.sessions(remainingProposals);
            scheduledSessions.addAll(sessions);
            trackCount++;
            tracks.add(Track.builder().session(sessions).trackCode(TRACK+trackCount).build());
            remainingProposals = ApplicationUtil.getListDifference(scheduledSessions, proposals);
        }

        return Tracks.builder().tracks(tracks).build();
    }



}
