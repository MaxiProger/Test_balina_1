package com.example.kolot.test_balina_1.networking.dto.Images;

import java.util.ArrayList;

/**
 * Created by kolot on 21.01.2018.
 */

public class GetImageDto {
    private int status;
    private ArrayList<postImageDto> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<postImageDto> getData() {
        return data;
    }

    public void setData(ArrayList<postImageDto> data) {
        this.data = data;
    }
}
