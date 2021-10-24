package fr.android.tennistrackerv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.Model.Format;
import fr.android.tennistrackerv2.Model.FormatLastMatch;
import fr.android.tennistrackerv2.SpinnerAdapter.SpinnerAdapter;
import fr.android.tennistrackerv2.ViewModel.ClubViewModel;
import fr.android.tennistrackerv2.ViewModel.FormatLastMatchViewModel;
import fr.android.tennistrackerv2.ViewModel.FormatViewModel;

public class CreateMatchActivity extends AppCompatActivity {
    private FormatViewModel formatViewModel;
    private FormatLastMatchViewModel formatLastMatchViewModel;
    private ClubViewModel clubViewModel;
    private ArrayAdapter<Format> adapterformatMatch;
    private ArrayAdapter<FormatLastMatch> adapterformatLastMatch;
    private ArrayAdapter<Club> clubArrayAdapter;
    Spinner formatMatchSpinner;
    Spinner formatLastMatchSpinner;
    Spinner spinnerClub1;
    Spinner spinnerClub2;
    Club club1;
    Club club2;
    EditText lieuMatchTxt;
    ImageButton btnLocation;
    Button BtnStartMatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);
        formatMatchSpinner = findViewById(R.id.formatMatchSpinner);
        formatLastMatchSpinner = findViewById(R.id.formatLastMatch);
        spinnerClub1 = findViewById(R.id.spinnerClub1);
        spinnerClub2 = findViewById(R.id.spinnerClub2);
        btnLocation = findViewById(R.id.btnLocation);
        lieuMatchTxt = findViewById(R.id.lieuMatchTxt);
        BtnStartMatch = findViewById(R.id.BtnStartMatch);

        formatViewModel = new ViewModelProvider(this).get(FormatViewModel.class);
        formatLastMatchViewModel = new ViewModelProvider(this).get(FormatLastMatchViewModel.class);
        clubViewModel = new ViewModelProvider(this).get(ClubViewModel.class);

        clubViewModel.getClubData().observe(this, clubs -> {
            SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, R.layout.custom_spinner_club_adapter,clubs );
            spinnerAdapter.notifyDataSetChanged();
            //spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerClub1.setAdapter(spinnerAdapter);
        });

        clubViewModel.getClubData().observe(this, clubs -> {
            SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, R.layout.custom_spinner_club_adapter,clubs );
            spinnerAdapter.notifyDataSetChanged();
            //spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerClub2.setAdapter(spinnerAdapter);
        });


        formatViewModel.getFormatList().observe(this, formats -> {

            adapterformatMatch = new ArrayAdapter<>(CreateMatchActivity.this, android.R.layout.simple_spinner_item, formats);
            adapterformatMatch.notifyDataSetChanged();
            adapterformatMatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            formatMatchSpinner.setAdapter(adapterformatMatch);
        });


        formatLastMatchViewModel.getFormatLastMatchList().observe(this, formatLastMatches -> {

            adapterformatLastMatch = new ArrayAdapter<>(CreateMatchActivity.this, android.R.layout.simple_spinner_item, formatLastMatches);
            adapterformatLastMatch.notifyDataSetChanged();
            adapterformatLastMatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            formatLastMatchSpinner.setAdapter(adapterformatLastMatch);
        });
        // Prevent to select two times same club
        spinnerClub1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == spinnerClub2.getSelectedItemId()) {
                    spinnerClub2.setSelection(i + 1);
                    club1 = (Club) spinnerClub1.getSelectedItem();
                    System.out.println("CLUB 2" + club2);
                } else {
                    club1 = (Club) spinnerClub1.getSelectedItem();
                    club2 = (Club) spinnerClub2.getSelectedItem();
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                club1 = (Club) spinnerClub1.getSelectedItem();
                club2 = (Club) spinnerClub2.getSelectedItem();
            }
        });

        spinnerClub2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == spinnerClub1.getSelectedItemId()) {
                    spinnerClub1.setSelection(i + 1);
                } else {
                    club1 = (Club) spinnerClub1.getSelectedItem();
                }
                club2 = (Club) spinnerClub2.getSelectedItem();
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                club1 = (Club) spinnerClub1.getSelectedItem();
                club2 = (Club) spinnerClub2.getSelectedItem();

            }
        });
        System.out.println(club1+"x"+club2);
        BtnStartMatch.setOnClickListener(view -> goToMatch());

    }



    public void goToMatch() {

        Intent newMatchForm = new Intent(this, MatchActivity2.class);
        newMatchForm.putExtra("club1", club1);
        newMatchForm.putExtra("club2", club2);
        startActivity(newMatchForm);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}