package com.thoughtworks.interview.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.thoughtworks.interview.util.ReflectionBuilder.builderFor;

public class Proposal {

    private String title;
    private long duration;

    public interface ProposalBuilder {
        ProposalBuilder title(String title);
        ProposalBuilder duration(long duration);
        Proposal build();
    }

    public static ProposalBuilder builder() {
        return builderFor(ProposalBuilder.class);
    }

    public String getTitle() {
        return title;
    }

    public long getDuration() {
        return duration;
    }

}
