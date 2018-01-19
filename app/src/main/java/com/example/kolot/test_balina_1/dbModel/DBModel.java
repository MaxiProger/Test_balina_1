package com.example.kolot.test_balina_1.dbModel;

import com.orm.SugarRecord;

/**
 * Created by kolot on 17.01.2018.
 */

public class DBModel extends SugarRecord<DBModel>{
    private String comment;
    private String urlImage;

    public DBModel() {
    }

    public DBModel(String comment, String urlImage) {

        this.comment = comment;
        this.urlImage = urlImage;
    }
}
