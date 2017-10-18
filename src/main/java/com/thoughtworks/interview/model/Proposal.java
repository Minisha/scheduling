package com.thoughtworks.interview.model;

import static com.thoughtworks.interview.util.ReflectionBuilder.builderFor;

public class Proposal {

    private String title;
    private String duration;
    private ProposalType proposalType;

    public interface ProposalBuilder {
        ProposalBuilder title(String title);
        ProposalBuilder duration(String duration);
        Proposal build();
    }

    public static ProposalBuilder builder() {
        return builderFor(ProposalBuilder.class);
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

}
