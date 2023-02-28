package ru.n3studio.calendar_of_holidays.Convectror;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Welcome {
    private String datesdf;
    private Holiday[] holidays;
    private String date;

    @JsonProperty("datesdf")
    public String getDatesdf() {
        return datesdf;
    }

    @JsonProperty("datesdf")
    public void setDatesdf(String value) {
        this.datesdf = value;
    }

    @JsonProperty("holidays")
    public Holiday[] getHolidays() {
        return holidays;
    }

    @JsonProperty("holidays")
    public void setHolidays(Holiday[] value) {
        this.holidays = value;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String value) {
        this.date = value;
    }
}
