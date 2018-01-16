package com.example.kolot.test_balina_1.networking.api;

import com.example.kolot.test_balina_1.networking.dto.UserDTO;
import com.example.kolot.test_balina_1.networking.dto.registrationDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by kolot on 15.01.2018.
 */

public interface Api_Registration {


    @POST("signup")
    Call<registrationDTO> signup(@Body UserDTO userDTO);

    @POST("signin")
    Call<registrationDTO> signin(@Body UserDTO userDTO);
}
