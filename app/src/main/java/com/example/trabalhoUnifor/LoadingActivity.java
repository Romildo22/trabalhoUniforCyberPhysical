package com.example.trabalhoUnifor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class LoadingActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_activity);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override public void run() {
                mostrarLogin();
            }
        },5000);
    }

    private void mostrarLogin() {
        Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}