package com.thoughtworks.interview.service;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;

public class SchedulerImpl {

    // 9am and must finish by 12 noon
    //1pm and 4.30, 4.45 pm

    //sort the proposals based on duration small to large
    public void getSessionSchedule(final Instant startTime, final Instant endTime, List<Proposal> proposals) {



        long difference = startTime.getEpochSecond() - endTime.getEpochSecond();

        proposals.stream().forEach(p -> {
            DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
           // System.out.printf("asda:" +startTime.toString());
            Date myDate = Date.from(startTime);
            String format = dateFormat.format(myDate);
            System.out.printf("format "+format);
            Session session = Session.builder().title(p.getTitle()).time(format).build();




        });
    }



}
