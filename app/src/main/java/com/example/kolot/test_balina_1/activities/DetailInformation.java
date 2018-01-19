package com.example.kolot.test_balina_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailInformation extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);

        textView = (TextView) findViewById(R.id.detailTextView);

        Intent intent=getIntent();
        String date = intent.getStringExtra("date");
        textView.setText(date);

    }
}
