package com.thoughtworks.interview.model;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;

public enum  ProposalType {

    LIGHTENING("lightning", "5min");

    private final String type;
    private final String duration;

    ProposalType(String type, String duration) {
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

    public String getDuration() {
        return duration;
    }
}
