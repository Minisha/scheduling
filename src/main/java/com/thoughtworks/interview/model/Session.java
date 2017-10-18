package com.thoughtworks.interview.model;


import static com.thoughtworks.interview.util.ReflectionBuilder.builderFor;

public class Session {

    private String title;
    private String startTime;
    private long duration;

    public interface SessionBuilder {
        SessionBuilder title(String title);
        SessionBuilder duration(long duration);
        SessionBuilder startTime(String startTime);
        Session build();
    }

    public static SessionBuilder builder() {
        return builderFor(SessionBuilder.class);
    }

    public String getTitle() {
        return title;
    }

    public String getStartTime() {
        return startTime;
    }

    public long getDuration() {
        return duration;
    }
}

