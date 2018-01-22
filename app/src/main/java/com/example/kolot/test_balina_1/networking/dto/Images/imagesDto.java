package com.example.kolot.test_balina_1.networking.dto.Images;

/**
 * Created by kolot on 20.01.2018.
 */

public class imagesDto {
    private String base64Image;
    private int date;
    private int lat;
    private int lng;

    public imagesDto(String base64Image, int date, int lat, int lng) {
        this.base64Image = base64Image;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
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
}
