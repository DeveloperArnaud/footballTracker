package fr.android.tennistrackerv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.android.tennistrackerv2.Fragment.StatistiqueFragments.LocationStatsFragment;
import fr.android.tennistrackerv2.Fragment.StatistiqueFragments.PictureStatsFragment;
import fr.android.tennistrackerv2.Fragment.StatistiqueFragments.StatistiqueFragment;
import fr.android.tennistrackerv2.Model.Match;

public class StatsMatchBottomBarActivity extends AppCompatActivity {

    final Fragment fragment1 = new StatistiqueFragment();
    final Fragment fragment2 = new LocationStatsFragment();
    final Fragment fragment3 = new PictureStatsFragment();
    Fragment active = fragment1;
    Bundle matchBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_match_bottom_bar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_stats);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        Intent i = getIntent();
        Match match = (Match) i.getSerializableExtra("match");

        matchBundle = new Bundle();
        matchBundle.putSerializable("match", match);




        fragment1.setArguments(matchBundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment1,"1").commit();
        fragment2.setArguments(matchBundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment2,"2").hide(fragment2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment3,"3").hide(fragment3).commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.statsBoard:
                            fragment1.setArguments(matchBundle);
                            getSupportFragmentManager().beginTransaction().hide(active).show(fragment1).commit();
                            active = fragment1;
                            break;
                        case R.id.localisation_match_stats:
                            fragment2.setArguments(matchBundle);
                            getSupportFragmentManager().beginTransaction().hide(active).show(fragment2).commit();
                            active = fragment2;
                            break;
                        case R.id.picture_match_stats:
                            getSupportFragmentManager().beginTransaction().hide(active).show(fragment3).commit();
                            active = fragment3;
                            break;
                    }
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment, "selected_fragment").commit();
                    return true;
                }
            };

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        // Nombre d'entrées si > 0 alors un fragment est instancié.
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if(count >= 0) {
            if (count == 0) {
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            } else {
                //Appliquer la transition sur le dernier fragment pour eviter de clear l'activity
                getSupportFragmentManager().popBackStack();
            }
        } else {
            //Si aucun fragment, on clear l'activity
            Intent i = new Intent(this, HistoryActivity.class);
            // L'activité en cours de lancement(HomeActivity) est déjà en cours d'exécution dans la tâche actuelle,
            // au lieu de lancer une nouvelle instance de cette activité, toutes les autres activités situées au-dessus seront fermées et
            // cet intent sera transmise à l'ancienne activité (maintenant au-dessus) comme une nouvelle intention.
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

    }
}