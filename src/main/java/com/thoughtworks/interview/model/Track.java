package com.thoughtworks.interview.model;

import java.util.List;

import static com.thoughtworks.interview.util.ReflectionBuilder.builderFor;

public class Track {

    private String trackCode;
    private List<Session> session;

    public interface TrackBuilder {
        TrackBuilder trackCode(String trackCode);
        TrackBuilder session(List<Session> session);
        Track build();
    }

    public static TrackBuilder builder() {
        return builderFor(TrackBuilder.class);
    }

    public String getTrackCode() {
        return trackCode;
    }

    public List<Session> getSession() {
        return session;
    }
}
