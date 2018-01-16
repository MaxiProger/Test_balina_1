package com.example.kolot.test_balina_1.networking.dto;

/**
 * Created by kolot on 15.01.2018.
 */

public class registrationDTO {
    private int status;

    private  data data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public com.example.kolot.test_balina_1.networking.dto.data getData() {
        return data;
    }

    public void setData(com.example.kolot.test_balina_1.networking.dto.data data) {
        this.data = data;
    }

    public registrationDTO(int status, com.example.kolot.test_balina_1.networking.dto.data data) {
        this.status = status;
        this.data = data;
    }
}
