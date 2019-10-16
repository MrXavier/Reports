package com.crealytics.reports.util;

public enum Month {
    JANUARY("January"),
    FEBRUARY("February");

    Month(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
