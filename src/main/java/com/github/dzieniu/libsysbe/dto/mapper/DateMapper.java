package com.github.dzieniu.libsysbe.dto.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateMapper {

    public static String localDateToString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static LocalDateTime stringToLocalDate(String date) {
        date += " 00:00";
        return LocalDateTime.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }
}
