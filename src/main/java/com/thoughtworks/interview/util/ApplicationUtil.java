package com.thoughtworks.interview.util;

import com.thoughtworks.interview.model.Proposal;
import com.thoughtworks.interview.model.Session;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ApplicationUtil {

    private static final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("hh:mma").toFormatter();

    public static String time(LocalTime time) {
        final DateFormat dateFormat = new SimpleDateFormat("hh:mma");
        Date myDate = ApplicationUtil.toDate(time);
        String format = dateFormat.format(myDate);
        return format;
    }


    public static LocalTime dateStringToLocalTime(String time) {
        LocalTime localTime = LocalTime.parse(time, dateTimeFormatter);
        return localTime;
    }

    public static Date toDate(LocalTime localTime) {
        Instant instant = localTime.atDate(LocalDate.now())
                .atZone(ZoneId.systemDefault()).toInstant();
        return toDate(instant);
    }

    public static List<Proposal> getListDifference(List<Session> sessions, List<Proposal> proposals) {
        Map<String, Session> sessionMap = new HashMap<>();
        sessions.stream().forEach(s -> {
            if(sessionMap.get(s.getTitle()) == null)  {
                sessionMap.put(s.getTitle(), s);
            }
        });
        return proposals.stream().filter(p -> sessionMap.get(p.getTitle()) == null).collect(Collectors.toList());
    }

    public static List<Session> sort(List<Session> sessions) {
        Collections.sort(sessions, Comparator.comparing(s -> dateStringToLocalTime(s.getStartTime())));
        return sessions;
    }

    public static boolean containsSpecialCharacter(String data) {
        Pattern p = Pattern.compile("^[^\\\\<>]*$");
        Matcher m = p.matcher(data);
        return !m.find();
    }

    private static Date toDate(Instant instant) {
        BigInteger milis = BigInteger.valueOf(instant.getEpochSecond()).multiply(
                BigInteger.valueOf(1000));
        milis = milis.add(BigInteger.valueOf(instant.getNano()).divide(
                BigInteger.valueOf(1_000_000)));
        return new Date(milis.longValue());
    }

}
