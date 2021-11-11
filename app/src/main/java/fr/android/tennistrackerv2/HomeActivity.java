package fr.android.tennistrackerv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;


public class HomeActivity extends AppCompatActivity {


    Button btnLang;
    Button btnHistoric;
    Button btnNewMatch;
    Button btnStatsClubs;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();


        setContentView(R.layout.activity_home);


        btnLang = (Button) findViewById(R.id.btnLang);
        btnHistoric = (Button) findViewById(R.id.btnHistoric);
        btnNewMatch = (Button) findViewById(R.id.btnNewMatch);
        btnStatsClubs = (Button) findViewById(R.id.btnStatsClubs);

        btnLang.setOnClickListener(view -> showChangeLangDialog());
        btnHistoric.setOnClickListener(view -> goToHistoric());
        btnNewMatch.setOnClickListener(view -> goToNewMatchForm());
        btnStatsClubs.setOnClickListener(view -> {
            goToClubStatistiques();
        });
    }

    public void goToHistoric() {
        Intent historicIntent = new Intent(this, HistoryActivity.class);
        startActivity(historicIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void goToNewMatchForm() {
        Intent newMatchForm = new Intent(this, CreateMatchActivity.class);
        startActivity(newMatchForm);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void goToClubStatistiques() {
        Intent clubStatistiquesIntent = new Intent(this, ClubStatistiquesActivity.class);
        startActivity(clubStatistiquesIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void showChangeLangDialog() {
        String frLang = getResources().getString(R.string.frLang);
        String enLang = getResources().getString(R.string.enLang);
        final String [] langs = {frLang, enLang};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.chooseLangTitle));
        builder.setSingleChoiceItems(langs, -1, (dialogInterface, i) -> {
            if(i == 0) {
                setLocale("fr");
                recreate();
            } else if(i == 1) {
                setLocale("en");
                //
                recreate();
            }
            dialogInterface.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;

        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String lang = preferences.getString("My_Lang", "");


        if(!Locale.getDefault().getLanguage().equals(lang)) {
            setLocale(Locale.getDefault().getLanguage());
        } else {
            setLocale(lang);
        }
    }

}