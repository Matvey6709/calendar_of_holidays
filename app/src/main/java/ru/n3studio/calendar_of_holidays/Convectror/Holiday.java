package ru.n3studio.calendar_of_holidays.Convectror;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Holiday {
    private String date;
    private String title;
    private String shortDescription;
    private String description;
    private String imgURL;

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String value) {
        this.date = value;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.title = value;
    }

    @JsonProperty("shortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("shortDescription")
    public void setShortDescription(String value) {
        this.shortDescription = value;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.description = value;
    }

    @JsonProperty("imgUrl")
    public String getImgURL() {
        return imgURL;
    }

    @JsonProperty("imgUrl")
    public void setImgURL(String value) {
        this.imgURL = value;
    }
}
