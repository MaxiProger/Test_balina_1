package com.example.kolot.test_balina_1.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.activities.DetailInformation;
import com.example.kolot.test_balina_1.activities.Main2Activity;
import com.example.kolot.test_balina_1.adapters.RecyclerViewAdapter;
import com.example.kolot.test_balina_1.dbModel.Images;
import com.example.kolot.test_balina_1.networking.api.Api;
import com.example.kolot.test_balina_1.networking.dto.Comments.CommentsDto;
import com.example.kolot.test_balina_1.networking.dto.Images.GetImageDto;
import com.example.kolot.test_balina_1.networking.dto.Images.postImageDto;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kolot on 15.01.2018.
 */

public class PhotosFragment extends Fragment {

    final String BASE_URL = "http://junior.balinasoft.com/api/";

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private SwipyRefreshLayout refreshLayout;
    private ArrayList<postImageDto> data = new ArrayList<>();
    public static int i = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photos_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.rView);
        refreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.refresh);

        refreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                getImages(++i);
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
            }
        });
        adapter = new RecyclerViewAdapter(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(new RecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onClick(String s, int date) {
                Intent intent = new Intent(getContext(), DetailInformation.class);
                intent.putExtra("url", s);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
        adapter.setOnLongClickListener(new RecyclerViewAdapter.ItemLongClickListener() {
            @Override
            public void onLongClick(int id) {
                Log.e("clicked", String.valueOf(id));

                CreateAlertDialog(id);
                i=0;
            }
        });
        getImages(i);
        List<Images> images = Images.listAll(Images.class);
        for (int i = 0; i < images.size(); i++)
            getComments(images.get(i).getPhotoId());
    }

    public void getComments(int imageId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api_comment = retrofit.create(Api.class);

        api_comment.getComments(imageId, Main2Activity.token)
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

    public void CreateAlertDialog(final int itemId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Do you want to delete this photo?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removePhoto(itemId);
                        adapter.refreshDates(itemId);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Delete");
        alertDialog.show();
    }


    public void getImages(int page) {
        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api_image = retrofit.create(Api.class);
        api_image.download(page, Main2Activity.token)
                .enqueue(new Callback<GetImageDto>() {
                    @Override
                    public void onResponse(@NonNull Call<GetImageDto> call, @NonNull Response<GetImageDto> response) {

                        Log.e("responsePost", response.message());
                        Log.e("responsePost", String.valueOf(response.raw()));
                        data = response.body().getData();
                        adapter.addDates(data);
                        if (response.body().getData().isEmpty()) i--;
                    }

                    @Override
                    public void onFailure(@NonNull Call<GetImageDto> call, @NonNull Throwable t) {
                        Log.e("responsePost", t.getMessage());
                    }
                });
    }

    public void removePhoto(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api_image_remove = retrofit.create(Api.class);

        api_image_remove.remove(id, Main2Activity.token).enqueue(new Callback<GetImageDto>() {
            @Override
            public void onResponse(Call<GetImageDto> call, Response<GetImageDto> response) {
                Log.e("delete", String.valueOf(response.raw()));
                adapter.notifyDataSetChanged();
                Snackbar.make(getView(), "Deleted.", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<GetImageDto> call, Throwable t) {
                Log.e("delete", t.getMessage());
            }
        });
    }


}
