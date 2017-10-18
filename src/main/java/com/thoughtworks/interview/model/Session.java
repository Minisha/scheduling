package com.thoughtworks.interview.model;

import java.time.Instant;

import static com.thoughtworks.interview.util.ReflectionBuilder.builderFor;

public class Session {

    private String title;
    private String time;

    public interface SessionBuilder {
        SessionBuilder title(String title);
        SessionBuilder time(String time);
        Session build();
    }

    public static SessionBuilder builder() {
        return builderFor(SessionBuilder.class);
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }
}

