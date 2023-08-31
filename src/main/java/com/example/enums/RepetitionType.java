package com.example.enums;

public enum RepetitionType {
    day,
    week,
    month,
    year;
    public static RepetitionType getRepetitionType(String value) {
        RepetitionType repetitionType = null;
        try {
            if (value != null && !value.isEmpty()) {
                repetitionType = RepetitionType.valueOf(value.toLowerCase());
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return repetitionType;
    }
}
