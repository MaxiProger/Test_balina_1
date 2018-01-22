package com.example.kolot.test_balina_1.networking.dto.Comments;

/**
 * Created by kolot on 21.01.2018.
 */

public class CommentsGetDto {
    private int id;
    private int date;
    private String text;

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {

        return id;
    }

    public int getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}
