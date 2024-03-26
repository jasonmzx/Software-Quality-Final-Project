package com.ontariotechu.sofe3980U.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Utility {
     public static LocalDate parseDate(String dateString, String format) {
        // Create a formatter using the given format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        
        // Parse the date string into a LocalDate object
        return LocalDate.parse(dateString, formatter);
    }
}
