package com.example.mpassignment2;

import static com.example.mpassignment2.utils.Constants.SPLASH_SCREEN_DELAY;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mpassignment2.view.MovieSearchActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Not using view binding as this is a splash screen and does not interact with the user
        setContentView(R.layout.activity_main);

        // Delay the splash screen for 2 seconds
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, MovieSearchActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_DELAY);
    }
}
