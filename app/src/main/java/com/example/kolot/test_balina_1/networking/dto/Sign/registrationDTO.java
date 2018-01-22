package com.example.kolot.test_balina_1.networking.dto.Sign;

/**
 * Created by kolot on 15.01.2018.
 */

public class registrationDTO {
    private int status;

    private RegistrationData data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RegistrationData getData() {
        return data;
    }

    public void setData(RegistrationData data) {
        this.data = data;
    }

}
