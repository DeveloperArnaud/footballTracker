package fr.android.tennistrackerv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;

import fr.android.tennistrackerv2.Adapter.ClubStatsAdapter;
import fr.android.tennistrackerv2.Adapter.MatchAdapter;
import fr.android.tennistrackerv2.Database.DatabaseManager;
import fr.android.tennistrackerv2.ViewModel.ClubViewModel;
import fr.android.tennistrackerv2.ViewModel.MatchViewModel;

public class ClubStatistiquesActivity extends AppCompatActivity {

    DatabaseManager databaseManager;
    RecyclerView recyclerView;
    ClubStatsAdapter clubStatsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_statistiques);

        databaseManager = new DatabaseManager(this);

        recyclerView = findViewById(R.id.club_stats_rw);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clubStatsAdapter = new ClubStatsAdapter(this, databaseManager.getClubStats());
        recyclerView.setAdapter(clubStatsAdapter);


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}