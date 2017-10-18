package com.thoughtworks.interview.model;

import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.interview.util.ReflectionBuilder.builderFor;

public class Tracks {

   List<Track> tracks;

    public interface TracksBuilder {
        TracksBuilder tracks(List<Track> tracks);
        Tracks build();
    }

    public static TracksBuilder builder() {
        return builderFor(TracksBuilder.class);
    }

    public List<Track> getTracks() {
        return (tracks == null)? new ArrayList<>() : tracks;
    }
}
