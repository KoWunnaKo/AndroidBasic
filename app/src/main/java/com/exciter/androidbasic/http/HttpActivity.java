package com.exciter.androidbasic.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.exciter.androidbasic.R;

import java.net.HttpURLConnection;

public class HttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        findViewById(R.id.btn_http_client_get).setOnClickListener(v -> {
            HttpClientTest httpClientTest = new HttpClientTest();
            httpClientTest.getData();
        });
        findViewById(R.id.btn_http_client_post).setOnClickListener(v -> {
            HttpClientTest httpClientTest = new HttpClientTest();
            httpClientTest.postData();
        });
        findViewById(R.id.btn_http_url_connection_post).setOnClickListener(v -> {
            HttpUrlConnectionTest test = new HttpUrlConnectionTest();
            test.postData();
        });
    }
}