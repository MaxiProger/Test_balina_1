package com.example.kolot.test_balina_1.dto;

/**
 * Created by kolot on 17.01.2018.
 */

public class commentsDto {

    private int date;
    private int id;
    private int lat;
    private int lng;
    private String url;

    public commentsDto(int date, int id, int lat, int lng, String url) {
        this.date = date;
        this.id = id;
        this.lat = lat;
        this.lng = lng;
        this.url = url;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
