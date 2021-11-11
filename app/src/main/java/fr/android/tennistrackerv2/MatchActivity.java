package fr.android.tennistrackerv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.android.tennistrackerv2.Fragment.LocationFragment;
import fr.android.tennistrackerv2.Fragment.MatchFragment;
import fr.android.tennistrackerv2.Fragment.PictureFragment;
import fr.android.tennistrackerv2.Model.Club;

public class MatchActivity extends AppCompatActivity {
    double lat, lng;
    Bundle bundle;
    Bundle bundleClub;
    String address;
    String title;
    String snippet;
    String photo_ref;
    Club club1;
    Club club2;
    final Fragment fragment1 = new MatchFragment();
    final Fragment fragment2 = new LocationFragment();
    final Fragment fragment3 = new PictureFragment();
    Fragment active = fragment1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent i = getIntent();
        address = i.getStringExtra("address");
        lat = i.getDoubleExtra("lat", 0);
        lng = i.getDoubleExtra("lng", 0);
        title = i.getStringExtra("title");
        snippet = i.getStringExtra("snippet");

        club1 = (Club) i.getSerializableExtra("club1");
        club2 = (Club) i.getSerializableExtra("club2");

        bundle = new Bundle();
        bundleClub = new Bundle();

        bundle.putDouble("lat", lat);
        bundle.putDouble("lng", lng);
        bundle.putString("title", title);
        bundle.putString("snippet", snippet);
        bundle.putString("photo_ref", photo_ref);

        bundleClub.putSerializable("club1", club1);
        bundleClub.putSerializable("club2", club2);
        bundleClub.putString("address", address);
        bundleClub.putString("title", title);
        bundleClub.putString("snippet", snippet);

        bundleClub.putDouble("lat", lat);
        bundleClub.putDouble("lng", lng);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        fragment1.setArguments(bundleClub);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment1, "1").commit();
        fragment2.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();

    }
    // Gestion des fragments
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    //Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.matchBoard:
                            fragment1.setArguments(bundleClub);
                            getSupportFragmentManager().beginTransaction().hide(active).show(fragment1).commit();
                            active = fragment1;
                            break;
                        case R.id.localisationPage:
                            fragment2.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction().hide(active).show(fragment2).commit();
                            active = fragment2;
                            break;
                        case R.id.picturePage:
                            getSupportFragmentManager().beginTransaction().hide(active).show(fragment3).commit();
                            active = fragment3;
                            break;
                    }
                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment, "selected_fragment").commit();
                    return true;
                }
            };


    private void showDialogCancelMatch() {
        String messageCancelMatch = getResources().getString(R.string.dialog_cancel_match);
        String btnYesString = getResources().getString(R.string.yes_txt_btn);
        String btnNoString = getResources().getString(R.string.no_txt_btn);
        new AlertDialog.Builder(this)
                .setMessage(messageCancelMatch)
                .setPositiveButton(btnYesString, (dialogInterface, i) -> {
                    finish();
                    Intent intent = new Intent(this, CreateMatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                })
                .setNegativeButton(btnNoString, null)
                .show();

    }

    @Override
    public void onBackPressed() {
        showDialogCancelMatch();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
    }
}