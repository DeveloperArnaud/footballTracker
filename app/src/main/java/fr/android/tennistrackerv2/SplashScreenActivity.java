package fr.android.tennistrackerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    Intent splashScreenIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            splashScreenIntent = new Intent(this,  HomeActivity.class);
            splashScreenIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(splashScreenIntent);
            finish();
        }, 3000);

    }

}