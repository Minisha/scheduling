package com.thoughtworks.interview.model;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

public enum  ProposalType {

    LIGHTENING("lightning", Instant.ofEpochSecond(5*60));

    private final String type;
    private final Instant duration;

    ProposalType(String type, Instant duration) {
        this.type = type;
        this.duration = duration;
    }

    public static Optional<ProposalType> type(String type) {
        ProposalType[] values = ProposalType.values();
        return Arrays.asList(values).stream().filter(v -> type.contains(v.getType())).findFirst();
    }

    public String getType() {
        return type;
    }

    public Instant getDuration() {
        return duration;
    }
}
