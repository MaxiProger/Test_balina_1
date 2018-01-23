package com.example.kolot.test_balina_1.activities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.adapters.RecyclerViewAdapter;
import com.example.kolot.test_balina_1.networking.api.Api;
import com.example.kolot.test_balina_1.networking.dto.Images.imagesDto;
import com.example.kolot.test_balina_1.networking.dto.Images.postImageStatusDto;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PreviewActivity extends AppCompatActivity  {

    private ImageView previewImageView;
    private Button previewCancel, previewUpload;
    private String token;
    private String encoded;
    private String pathBitmap;
    private Retrofit retrofit;
    private double lat, lon;
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
        /*previewCancel = (Button) findViewById(R.id.cancel);
        previewUpload = (Button) findViewById(R.id.upload);*/

        previewImageView.setImageBitmap(BitmapFactory.decodeFile(pathBitmap));/*
        previewUpload.setOnClickListener(this);
        previewCancel.setOnClickListener(this);*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.preview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_send:
                uploadImage(encoded, lat,lon);
                finish();
                return true;
                default:return super.onOptionsItemSelected(item);
        }


    }

   /* @Override
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
*/

    public void uploadImage(String base64, double lat, double lon) {

        Api api_image = retrofit.create(Api.class);

        api_image.upload(new imagesDto(base64, Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date())), (int) lat, (int) lon), token)
                .enqueue(new Callback<postImageStatusDto>() {
                    @Override
                    public void onResponse(@NonNull Call<postImageStatusDto> call, @NonNull Response<postImageStatusDto> response) {
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplication());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(@NonNull Call<postImageStatusDto> call, @NonNull Throwable t) {
                        Log.e("responsePost: " , t.getMessage());
                    }
                });
    }
}
