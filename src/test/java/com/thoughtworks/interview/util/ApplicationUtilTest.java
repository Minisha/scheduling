package com.thoughtworks.interview.util;

import org.junit.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationUtilTest {

    @Test
    public void should_convert_time_to_date_string() {
        String time = ApplicationUtil.time(LocalTime.of(9, 0));
        assertThat(time).isEqualTo("09:00AM");
    }

    @Test
    public void should_convert_string_to_localtime() {
        LocalTime localTime = ApplicationUtil.dateStringToLocalTime("09:00AM");
        assertThat(localTime.getHour()).isEqualTo(9);
    }

}
