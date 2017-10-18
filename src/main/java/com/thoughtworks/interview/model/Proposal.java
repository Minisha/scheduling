package com.thoughtworks.interview.model;

import java.time.Instant;

import static com.thoughtworks.interview.util.ReflectionBuilder.builderFor;

public class Proposal {

    private String title;
    private Instant duration;

    public interface ProposalBuilder {
        ProposalBuilder title(String title);
        ProposalBuilder duration(Instant duration);
        Proposal build();
    }

    public static ProposalBuilder builder() {
        return builderFor(ProposalBuilder.class);
    }

    public String getTitle() {
        return title;
    }

    public Instant getDuration() {
        return duration;
    }

}
