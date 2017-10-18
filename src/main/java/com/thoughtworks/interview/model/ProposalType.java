package com.thoughtworks.interview.model;

import com.thoughtworks.interview.util.ApplicationUtil;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

public enum  ProposalType {

    LIGHTENING("lightning", 5);

    private final String type;
    private final long duration;

    ProposalType(String type, long duration) {
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

    public long getDuration() {
        return duration;
    }
}
