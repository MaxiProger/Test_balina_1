package com.example.kolot.test_balina_1.dbModel;

import com.orm.SugarRecord;

/**
 * Created by kolot on 17.01.2018.
 */

public class Images extends SugarRecord<Images> {
    public int getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    private int date;
    private String url;
    private int photoId;
    private int lat;
    private int lon;


    public Images() {
    }

    public Images(int date, String url, int photoId, int lat, int lon) {
        this.date = date;
        this.url = url;
        this.photoId = photoId;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Images{" +
                "date=" + date +
                ", url='" + url + '\'' +
                ", photoId=" + photoId +
                ", lat=" + lat +
                ", lon=" + lon +
                ", id=" + id +
                '}';
    }
}
