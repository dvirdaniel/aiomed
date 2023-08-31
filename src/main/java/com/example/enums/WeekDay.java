package com.example.enums;

public enum WeekDay {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;
    public static WeekDay getWeekDay(String value) {
        WeekDay weekDay = null;
        try {
            if (value != null && !value.isEmpty()) {
                weekDay = WeekDay.valueOf(value.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return weekDay;
    }
}
