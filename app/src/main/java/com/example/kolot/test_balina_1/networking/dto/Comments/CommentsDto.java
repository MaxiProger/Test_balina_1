package com.example.kolot.test_balina_1.networking.dto.Comments;

import java.util.List;

/**
 * Created by kolot on 17.01.2018.
 */

public class CommentsDto {

    private int status;
    private List<CommentsGetDto> data;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<CommentsGetDto> data) {
        this.data = data;
    }

    public int getStatus() {

        return status;
    }

    public List<CommentsGetDto> getData() {
        return data;
    }
}
