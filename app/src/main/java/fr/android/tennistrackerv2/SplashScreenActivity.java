package fr.android.tennistrackerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    Intent splashScreenIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            splashScreenIntent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(splashScreenIntent);
            finish();
        }, 3000);

    }
}