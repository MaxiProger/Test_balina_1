package com.example.kolot.test_balina_1.activities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.networking.api.Api;
import com.example.kolot.test_balina_1.networking.dto.Comments.CommentsDto;
import com.example.kolot.test_balina_1.networking.dto.Images.GetImageDto;
import com.example.kolot.test_balina_1.networking.dto.Images.imagesDto;
import com.example.kolot.test_balina_1.networking.dto.Images.postImageStatusDto;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView previewImageView;
    private Button previewCancel, previewUpload;
    private String token;
    private String encoded;
    private String pathBitmap;
    private Retrofit retrofit;
    private double lat, lon;
    private int id = 0;
    private String url = "";
    final String BASE_URL = "http://junior.balinasoft.com/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            token = bundle.getString("token");
            encoded = bundle.getString("base64");
            pathBitmap = bundle.getString("pathBitmap");
            lat = bundle.getDouble("lat");
            lon = bundle.getDouble("lon");
            Log.e("lat: ", String.valueOf(lat));
            Log.e("ont: ", String.valueOf(lon));

        }
        previewImageView = (ImageView) findViewById(R.id.preview);
        previewCancel = (Button) findViewById(R.id.cancel);
        previewUpload = (Button) findViewById(R.id.upload);

        previewImageView.setImageBitmap(BitmapFactory.decodeFile(pathBitmap));
        previewUpload.setOnClickListener(this);
        previewCancel.setOnClickListener(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                finish();
                break;

            case R.id.upload:
                uploadImage(encoded, lat,lon);
                finish();
                break;
        }
    }


    public void uploadImage(String base64, double lat, double lon) {

        Api api_image = retrofit.create(Api.class);

        api_image.upload(new imagesDto(base64, Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date())), (int) lat, (int) lon), token)
                .enqueue(new Callback<postImageStatusDto>() {
                    @Override
                    public void onResponse(@NonNull Call<postImageStatusDto> call, @NonNull Response<postImageStatusDto> response) {
                        Log.e("responsePost", response.body().getData().getUrl());
                        Log.e("responsePost", String.valueOf(response.body().getData().getId()));
                        Log.e("responsePost", String.valueOf(response.body().getData().getLat()));
                        Log.e("responsePost", String.valueOf(response.body().getData().getLng()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<postImageStatusDto> call, @NonNull Throwable t) {
                        Log.e("responsePost", t.getMessage());
                    }
                });
    }

    public void getImages() {
        Api api_image = retrofit.create(Api.class);
        api_image.download(0, token)
                .enqueue(new Callback<GetImageDto>() {
                    @Override
                    public void onResponse(Call<GetImageDto> call, Response<GetImageDto> response) {

                        Log.e("responsePost", response.message());
                        Log.e("responsePost", String.valueOf(response.body()));
                        Log.e("responsePost", String.valueOf(response.raw()));
                    }

                    @Override
                    public void onFailure(Call<GetImageDto> call, Throwable t) {
                        Log.e("responsePost", t.getMessage());
                    }
                });
    }

    public void getComments(int imageId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api_comment = retrofit.create(Api.class);

        api_comment.getComments(imageId, 0, token)
                .enqueue(new Callback<CommentsDto>() {
                    @Override
                    public void onResponse(@NonNull Call<CommentsDto> call, @NonNull Response<CommentsDto> response) {
                        Log.e("comments: ", String.valueOf(response.raw()));
                    }

                    @Override
                    public void onFailure(@NonNull Call<CommentsDto> call, @NonNull Throwable t) {
                        Log.e("comments: ", t.getMessage());
                    }
                });
    }
}
