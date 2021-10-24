package fr.android.tennistrackerv2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;

import java.util.Locale;

import fr.android.tennistrackerv2.Callback.ISendDataFragment;

public class HomeActivity extends AppCompatActivity {


    Button btnLang;
    Button btnHistoric;
    Button btnNewMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadLocale();
        setContentView(R.layout.activity_home);

        btnLang = (Button) findViewById(R.id.btnLang);
        btnHistoric = (Button) findViewById(R.id.btnHistoric);
        btnNewMatch = (Button) findViewById(R.id.btnNewMatch);

        btnLang.setOnClickListener(view -> showChangeLangDialog());
        btnHistoric.setOnClickListener(view -> goToHistoric());
        btnNewMatch.setOnClickListener(view -> goToNewMatchForm());
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
            } else {
                setLocale("en");
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
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String lang = preferences.getString("My_Lang", "");
        setLocale(lang);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}