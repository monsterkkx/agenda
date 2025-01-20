package com.example.agenda;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView splashImage = findViewById(R.id.splash_image);
        Glide.with(this)
                .asGif()
                .load(R.drawable.giphy)
                .into(splashImage);

        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, ActivityMain.class);
            startActivity(intent);
            finish();
        },3000);
    }
}
