package com.example.kolot.test_balina_1.networking.api;

import com.example.kolot.test_balina_1.networking.dto.Comments.CommentsDto;
import com.example.kolot.test_balina_1.networking.dto.Images.GetImageDto;
import com.example.kolot.test_balina_1.networking.dto.Images.imagesDto;
import com.example.kolot.test_balina_1.networking.dto.Images.postImageStatusDto;
import com.example.kolot.test_balina_1.networking.dto.Sign.UserDTO;
import com.example.kolot.test_balina_1.networking.dto.Sign.registrationDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kolot on 21.01.2018.
 */

public interface Api {

    @GET("/image/{imageId}/comment")
    Call<CommentsDto> getComments (
            @Path("imageId") int imageId,
            @Query("page") int page,
            @Header("Access-Token") String token);

    @POST("image")
    Call<postImageStatusDto> upload(@Body imagesDto imagesDto,
                                    @Header("Access-Token") String token);

    @GET("image")
    Call<GetImageDto> download(@Query("page") int page,
                               @Header("Access-Token") String token);
    @DELETE ("image/{photo_Id}")
    Call<GetImageDto> remove(@Path("photo_Id") int id,
                             @Header("Access-Token") String token);

    @POST("signup")
    Call<registrationDTO> signup(@Body UserDTO userDTO);

    @POST("signin")
    Call<registrationDTO> signin(@Body UserDTO userDTO);

}
