package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Session;
import com.thoughtworks.interview.model.Track;
import com.thoughtworks.interview.model.Tracks;
import com.thoughtworks.interview.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

public class TrackManagerImpl implements TrackManager {

    @Override
    public Tracks getTracks(List<Proposal> proposals) {
        ScheduleManager manager = new ScheduleManagerImpl();
        List<Session> track1 = ApplicationUtil.sort(manager.sessions(proposals));
        List<Proposal> difference = ApplicationUtil.getListDifference(track1, proposals);
        List<Session> track2 = ApplicationUtil.sort(manager.sessions(difference));

        List<Track> tracks = new ArrayList<>();
        tracks.add(Track.builder().session(track1).trackCode("Track1").build());
        tracks.add(Track.builder().session(track2).trackCode("Track2").build());
        return Tracks.builder().tracks(tracks).build();
    }


}
