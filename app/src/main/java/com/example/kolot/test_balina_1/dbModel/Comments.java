package com.example.kolot.test_balina_1.dbModel;

import com.orm.SugarRecord;

/**
 * Created by kolot on 23.01.2018.
 */

public class Comments extends SugarRecord<Comments>{
     int idComment;
     int date;
     String text;

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Comments() {
    }

    public Comments(int idComment, int date, String text) {
        this.idComment = idComment;
        this.date = date;
        this.text = text;
    }

    public int getIdComment() {
        return idComment;
    }

    public int getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "idComment=" + idComment +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
