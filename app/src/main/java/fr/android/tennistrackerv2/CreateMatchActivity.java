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
import android.widget.TextView;
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
    Spinner spinnerClub1;
    Spinner spinnerClub2;
    Club club1;
    Club club2;
    EditText lieuMatchTxt;
    ImageButton btnLocation;
    Button BtnStartMatch;
    Intent i;
    String address;
    String title;
    String snippet;
    String photo_ref;
    double lat, lng;
    String latP;
    String lngtP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);
        spinnerClub1 = findViewById(R.id.spinnerClub1);
        spinnerClub2 = findViewById(R.id.spinnerClub2);
        btnLocation = findViewById(R.id.btnLocation);
        lieuMatchTxt = findViewById(R.id.lieuMatchTxt);
        BtnStartMatch = findViewById(R.id.BtnStartMatch);

        i = getIntent();

        address = i.getStringExtra("address");
        snippet = i.getStringExtra("snippet");
        title = i.getStringExtra("title");
        photo_ref = i.getStringExtra("photo_ref");


        if(address == null) {
            lieuMatchTxt.setText("");
        } else {
            lieuMatchTxt.setText(address);
        }

        lat = i.getDoubleExtra("lat", 0);
        lng =i.getDoubleExtra("lng",0);


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
            System.out.println(clubs);
            SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, R.layout.custom_spinner_club_adapter,clubs );
            spinnerAdapter.notifyDataSetChanged();
            //spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerClub2.setAdapter(spinnerAdapter);
        });


        formatViewModel.getFormatList().observe(this, formats -> {

            adapterformatMatch = new ArrayAdapter<>(CreateMatchActivity.this, android.R.layout.simple_spinner_item, formats);
            adapterformatMatch.notifyDataSetChanged();
            adapterformatMatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        });


        formatLastMatchViewModel.getFormatLastMatchList().observe(this, formatLastMatches -> {

            adapterformatLastMatch = new ArrayAdapter<>(CreateMatchActivity.this, android.R.layout.simple_spinner_item, formatLastMatches);
            adapterformatLastMatch.notifyDataSetChanged();
            adapterformatLastMatch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

        BtnStartMatch.setOnClickListener(view -> goToMatch());
        btnLocation.setOnClickListener(view -> {
            Intent intent = new Intent(CreateMatchActivity.this, NearbyStadiumActivity.class);
            startActivity(intent);
        });

    }



    public void goToMatch() {

        Intent newMatchForm = new Intent(this, TestActivity.class);

        if(club1 != null && club2 != null && address != null && lat > 0 & lng > 0) {
            newMatchForm.putExtra("club1", club1);
            newMatchForm.putExtra("club2", club2);
            newMatchForm.putExtra("lat", lat);
            newMatchForm.putExtra("lng", lng);
            newMatchForm.putExtra("address", address);
            newMatchForm.putExtra("title", title);
            newMatchForm.putExtra("snippet", snippet);
            newMatchForm.putExtra("photo_ref", photo_ref);
            startActivity(newMatchForm);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            Toast.makeText(CreateMatchActivity.this, "Veuillez sélectionner tous les champs nécessaires", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}