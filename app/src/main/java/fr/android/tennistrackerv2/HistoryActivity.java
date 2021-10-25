package fr.android.tennistrackerv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import fr.android.tennistrackerv2.Adapter.ImageUploadAdapter;
import fr.android.tennistrackerv2.Adapter.StatistiqueAdapter;
import fr.android.tennistrackerv2.ViewModel.StatistiqueViewModel;
import fr.android.tennistrackerv2.ViewModel.UploadViewModel;

public class HistoryActivity extends AppCompatActivity {

    private StatistiqueViewModel statistiqueViewModel;
    private String serialId;
    private RecyclerView recyclerView;
    private StatistiqueAdapter statistiqueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent i = getIntent();
        serialId = i.getStringExtra("serialId");
        if(serialId == null) {
            serialId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        System.out.println(serialId);
        recyclerView = findViewById(R.id.recycler_view_history);
        statistiqueViewModel = new ViewModelProvider(this).get(StatistiqueViewModel.class);
        statistiqueViewModel.getStatistiques(serialId).observe(this, statistiques -> {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            statistiqueAdapter = new StatistiqueAdapter(this, statistiques);
            recyclerView.setAdapter(statistiqueAdapter);
            statistiqueAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}