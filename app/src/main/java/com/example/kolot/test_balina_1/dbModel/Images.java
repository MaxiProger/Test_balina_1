package com.example.kolot.test_balina_1.dbModel;

import com.orm.SugarRecord;

/**
 * Created by kolot on 17.01.2018.
 */

public class Images extends SugarRecord<Images>{
    public int date;
    public String url;
    public int photoId;

    public Images() {
    }

    public Images(int photoId, int date , String url) {

        this.date = date;
        this.url = url;
        this.photoId = photoId;
    }

    @Override
    public String toString() {
        return "Images{" +
                "date=" + date +
                ", url='" + url + '\'' +
                ", photo_Id=" + photoId +", id=" + id +
                '}';
    }
}
