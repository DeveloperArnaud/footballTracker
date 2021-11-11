package fr.android.tennistrackerv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import fr.android.tennistrackerv2.Database.DatabaseManager;
import fr.android.tennistrackerv2.Model.ClubStats;
import fr.android.tennistrackerv2.Model.Statistique;

public class ClubStatsDetailsActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    ClubStats clubStats;
    Statistique statistique;
    ImageView imgView_logo_clubStats1;
    TextView txtNameClubStats;
    TextView butScore;
    TextView nbTirs;
    TextView nbTirCadres;
    TextView nbPasses;
    TextView nbFautes;
    TextView nbCartonJ;
    TextView nbCartonR;
    TextView nbOffsides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_stats_details);
        imgView_logo_clubStats1 = findViewById(R.id.imgView_logo_clubStats1);
        txtNameClubStats = findViewById(R.id.txtNameClubStats);
        butScore = findViewById(R.id.butScore);
        nbTirs = findViewById(R.id.nbTirs);
        nbPasses = findViewById(R.id.nbPasses);
        nbTirCadres = findViewById(R.id.nbTirCadres);
        nbFautes = findViewById(R.id.nbFautes);
        nbCartonJ = findViewById(R.id.nbCartonJ);
        nbCartonR = findViewById(R.id.nbCartonR);
        nbOffsides = findViewById(R.id.nbOffsides);

        Intent intent = getIntent();
        databaseManager = new DatabaseManager(this);
        clubStats = (ClubStats) intent.getSerializableExtra("clubStat");
        System.out.println(clubStats.getImgUrl());

        int id = clubStats.getId_stats();

       statistique =  databaseManager.getStatistiqueClubById(id);
        Picasso.with(this)
                .load(clubStats.getImgUrl())
                .centerCrop()
                .fit()
                .into(imgView_logo_clubStats1);
        txtNameClubStats.setText(clubStats.getName());
        butScore.setText(String.valueOf(statistique.getScore()));
        nbTirs.setText(String.valueOf(statistique.getTir()));
        nbTirCadres.setText(String.valueOf(statistique.getTirCadre()));
        nbPasses.setText(String.valueOf(statistique.getPasse()));
        nbFautes.setText(String.valueOf(statistique.getFautes()));
        nbCartonJ.setText(String.valueOf(statistique.getCartonJaune()));
        nbCartonR.setText(String.valueOf(statistique.getCartonRouge()));
        nbOffsides.setText(String.valueOf(statistique.getHorsJeu()));

    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}