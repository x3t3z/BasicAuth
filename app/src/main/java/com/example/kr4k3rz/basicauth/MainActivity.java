package com.example.kr4k3rz.basicauth;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView textShow;
    Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textShow = findViewById(R.id.tv_show);
        mainHandler = new Handler(Looper.getMainLooper());


        //initialise the okhttp client by authenticating by user:pass
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("admin", "1234"))
                .build();
        //make a request to the url to consume the api
        final Request request = new Request.Builder()
                .url("http://nepalapp.online/api/questions")
                .build();
        //call the okhttp client and make request to get the json data in Log
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                Log.d("TAG", response.body().string()); //here the json data is taken from response().body()
            }
        });
    }
}
