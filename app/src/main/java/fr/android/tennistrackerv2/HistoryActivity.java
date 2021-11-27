package fr.android.tennistrackerv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import fr.android.tennistrackerv2.Adapter.ImageUploadAdapter;
import fr.android.tennistrackerv2.Adapter.MatchAdapter;
import fr.android.tennistrackerv2.ViewModel.MatchViewModel;

public class HistoryActivity extends AppCompatActivity {

    private MatchViewModel matchViewModel;
    private String serialId;
    private RecyclerView recyclerView;
    private MatchAdapter matchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        serialId  = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        recyclerView = findViewById(R.id.recycler_view_history);
        matchViewModel = new ViewModelProvider(this).get(MatchViewModel.class);
        matchViewModel.getMatches(serialId).observe(this, matches -> {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            matchAdapter = new MatchAdapter(this, matches);
            recyclerView.setAdapter(matchAdapter);
            //Détecter les modifications
            matchAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}