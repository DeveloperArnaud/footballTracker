package fr.android.tennistrackerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class StatsMatchBottomBarActivity extends AppCompatActivity {

    private ImageView imgView_logo_club1;
    private ImageView imgView_logo_clubA;
    private TextView txtNameClub1;
    private TextView txtScoreClub1;
    private TextView tirsTxtA;
    private TextView tirCadreTxtA;
    private TextView passeTxtA;
    private TextView fautesTxtA;
    private TextView cartonJTxtA;
    private TextView cartonRTxtA;
    private TextView horsJeuTxtA;

    private ImageView imgView_logo_club2;
    private ImageView imgView_logo_clubB;
    private TextView txtNameClub2;
    private TextView txtScoreClub2;
    private TextView tirsTxtB;
    private TextView tirCadreTxtB;
    private TextView passeTxtB;
    private TextView fautesTxtB;
    private TextView cartonJTxtB;
    private TextView cartonRTxtB;
    private TextView horsJeuTxtB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_match_bottom_bar);

        imgView_logo_club1 = findViewById(R.id.imgView_logo_club1);
        imgView_logo_clubA = findViewById(R.id.imgView_logo_clubA);
        txtNameClub1 = findViewById(R.id.txtNameClub1);
        txtScoreClub1 = findViewById(R.id.txtScoreClub1);
        tirsTxtA = findViewById(R.id.tirsTxtA);
        tirCadreTxtA = findViewById(R.id.tirsCadresTxtA);
        passeTxtA = findViewById(R.id.passesTxtA);
        fautesTxtA = findViewById(R.id.fautesA);
        cartonJTxtA = findViewById(R.id.cartonJTxtA);
        cartonRTxtA = findViewById(R.id.cartonRTxtA);
        horsJeuTxtA = findViewById(R.id.horsJeuTxtA);

        imgView_logo_club2 = findViewById(R.id.imgView_logo_club2);
        imgView_logo_clubB = findViewById(R.id.imgView_logo_clubB);
        txtNameClub2 = findViewById(R.id.txtNameClub2);
        txtScoreClub2 = findViewById(R.id.txtScoreClub2);
        tirsTxtB = findViewById(R.id.tirsTxtB);
        tirCadreTxtB = findViewById(R.id.tirsCadresTxtB);
        passeTxtB = findViewById(R.id.passesTxtB);
        fautesTxtB = findViewById(R.id.fautesB);
        cartonJTxtB = findViewById(R.id.cartonJTxtB);
        cartonRTxtB = findViewById(R.id.cartonRTxtB);
        horsJeuTxtB = findViewById(R.id.horsJeuTxtB);

        Intent stats = getIntent();
        Bundle matchStats = stats.getExtras();

        Picasso.with(this)
                .load((String) matchStats.get("club1Img"))
                .fit()
                .centerCrop()
                .into(imgView_logo_club1);
        Picasso.with(this)
                .load((String) matchStats.get("club1Img"))
                .fit()
                .centerCrop()
                .into(imgView_logo_clubA);

        txtNameClub1.setText(clubNameTooLong((String) matchStats.get("club1Name")));
        txtScoreClub1.setText(String.valueOf(matchStats.get("club1Score")));
        tirsTxtA.setText(String.valueOf(matchStats.get("club1Tir")));
        tirCadreTxtA.setText(String.valueOf(matchStats.get("club1TirCadre")));
        passeTxtA.setText(String.valueOf(matchStats.get("club1Passe")));
        fautesTxtA.setText(String.valueOf(matchStats.get("club1Faute")));
        cartonJTxtA.setText(String.valueOf(matchStats.get("club1CartonJ")));
        cartonRTxtA.setText(String.valueOf(matchStats.get("club1CartonR")));
        horsJeuTxtA.setText(String.valueOf(matchStats.get("club1HorsJeu")));

        Picasso.with(this)
                .load((String) matchStats.get("club2Img"))
                .fit()
                .centerCrop()
                .into(imgView_logo_club2);
        Picasso.with(this)
                .load((String) matchStats.get("club2Img"))
                .fit()
                .centerCrop()
                .into(imgView_logo_clubB);

        txtNameClub2.setText(clubNameTooLong((String) matchStats.get("club2Name")));
        txtScoreClub2.setText(String.valueOf(matchStats.get("club2Score")));
        tirsTxtB.setText(String.valueOf(matchStats.get("club2Tir")));
        tirCadreTxtB.setText(String.valueOf(matchStats.get("club2TirCadre")));
        passeTxtB.setText(String.valueOf(matchStats.get("club2Passe")));
        fautesTxtB.setText(String.valueOf(matchStats.get("club2Faute")));
        cartonJTxtB.setText(String.valueOf(matchStats.get("club2CartonJ")));
        cartonRTxtB.setText(String.valueOf(matchStats.get("club2CartonR")));
        horsJeuTxtB.setText(String.valueOf(matchStats.get("club2HorsJeu")));
    }

    public String clubNameTooLong(String clubName) {
        String outString = "";
        if(clubName.trim().length() >= 10) {
            for(int i = 0; i < clubName.length(); i++) {
                char c = clubName.charAt(i);
                outString += (Character.isUpperCase(c) ? c + " " : "").trim();
            }
        } else {
            return clubName;
        }
        return outString;
    }
}