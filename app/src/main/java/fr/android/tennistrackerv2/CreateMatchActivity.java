package fr.android.tennistrackerv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.SpinnerAdapter.SpinnerAdapter;
import fr.android.tennistrackerv2.ViewModel.ClubViewModel;

public class CreateMatchActivity extends AppCompatActivity {
    private ClubViewModel clubViewModel;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);
        spinnerClub1 = findViewById(R.id.spinnerClub1);
        spinnerClub2 = findViewById(R.id.spinnerClub2);
        btnLocation = findViewById(R.id.btnLocation);
        lieuMatchTxt = findViewById(R.id.lieuMatchTxt);
        BtnStartMatch = findViewById(R.id.BtnStartMatch);

        lieuMatchTxt.setEnabled(false);

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

        clubViewModel = new ViewModelProvider(this).get(ClubViewModel.class);

        clubViewModel.getClubData().observe(this, clubs -> {
            SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, R.layout.custom_spinner_club_adapter,clubs );
            spinnerAdapter.notifyDataSetChanged();
            spinnerClub1.setAdapter(spinnerAdapter);
        });

        clubViewModel.getClubData().observe(this, clubs -> {
            System.out.println(clubs);
            SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, R.layout.custom_spinner_club_adapter,clubs );
            spinnerAdapter.notifyDataSetChanged();
            spinnerClub2.setAdapter(spinnerAdapter);
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

        if (i.getIntExtra("index1", 0) != 0 && i.getIntExtra("index2", 0) != 0) {
            int index1 = i.getIntExtra("index1", 0);
            int index2 = i.getIntExtra("index2", 0);

            spinnerClub1.setSelection(index1);
            spinnerClub2.setSelection(index2);
        }


        BtnStartMatch.setOnClickListener(view -> goToMatch());
        btnLocation.setOnClickListener(view -> {

            Intent intent = new Intent(CreateMatchActivity.this, NearbyStadiumActivity.class);
            startActivity(intent);
        });

    }



    public void goToMatch() {

        Intent newMatchForm = new Intent(this, MatchActivity.class);

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
            Toast.makeText(CreateMatchActivity.this, "Veuillez choisir un stade", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}