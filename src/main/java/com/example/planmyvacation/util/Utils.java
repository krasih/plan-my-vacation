package com.example.planmyvacation.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yy";

    public static Instant getInstant(LocalDate date) {

        return date.atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    public static LocalDate getLocalDate(Instant date) {

        return LocalDate.ofInstant(date, ZoneOffset.systemDefault());
    }

    public static String formatDate(LocalDate date, String format) {

        return date.format(DateTimeFormatter.ofPattern(format));
    }

    public static String formatDate(LocalDate date) {

        return formatDate(date, DEFAULT_DATE_FORMAT);
    }



}
