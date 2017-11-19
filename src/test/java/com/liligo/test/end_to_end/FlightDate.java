package com.liligo.test.end_to_end;

public class FlightDate {
    private String day;
    private String month;
    private String year;

    public FlightDate(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
}
