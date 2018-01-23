package com.example.kolot.test_balina_1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kolot.test_balina_1.R;
import com.example.kolot.test_balina_1.adapters.DetailInfoRVAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailInformation extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView imageView;
    private TextView textView;
    private DetailInfoRVAdapter adapter;
    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);
        ArrayList<String> info = new ArrayList<>();
        adapter = new DetailInfoRVAdapter(init(info));
        recyclerView = (RecyclerView) findViewById(R.id.detailRecyclerView);
        imageView = (ImageView) findViewById(R.id.detailImageView);
        textView = (TextView) findViewById(R.id.detailDateTV);
        imageView.setImageResource(R.drawable.ic_launcher);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        int date = intent.getIntExtra("date", 0);

        Picasso.with(this).load(url).into(imageView);
        textView.setText(String.valueOf(date));

    }

    public ArrayList<String> init(ArrayList<String> info) {
        for (int i = 0; i < 6; i++)
            info.add(String.valueOf(i));
        return info;
    }
}
