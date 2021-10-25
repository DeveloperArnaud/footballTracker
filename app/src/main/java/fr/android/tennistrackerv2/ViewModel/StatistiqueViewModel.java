package fr.android.tennistrackerv2.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import fr.android.tennistrackerv2.Callback.IClubCallbackListener;
import fr.android.tennistrackerv2.Callback.IStatistiqueCallbackListener;
import fr.android.tennistrackerv2.Common;
import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.Model.Statistique;

public class StatistiqueViewModel extends ViewModel implements IStatistiqueCallbackListener {

    private MutableLiveData<List<Statistique>> stat;
    private MutableLiveData<String> message;
    public IStatistiqueCallbackListener listener;


    public StatistiqueViewModel() {
        listener = this;
    }

    public MutableLiveData<List<Statistique>> getStatistiques(String serialId) {
        if(stat == null) {
            stat = new MutableLiveData<>();
            message = new MutableLiveData<>();
            loadStatistiqueData(serialId);
        }

        return stat;
    }

    private void loadStatistiqueData(String serialId) {
        List<Statistique> statistiques = new ArrayList<>();
        DatabaseReference stats = FirebaseDatabase.getInstance().getReference("Stats");
        Query checkSerialId = stats.orderByChild("serialId").equalTo(serialId);
        checkSerialId.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Statistique statistique = dataSnapshot.getValue(Statistique.class);
                    statistiques.add(statistique);
                }
                listener.onStatistiqueLoadSuccess(statistiques);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onStatistiqueLoadFailed(error.getMessage());
            }
        });
    }


    @Override
    public void onStatistiqueLoadSuccess(List<Statistique> statistiqueList) {
        stat.setValue(statistiqueList);
    }

    @Override
    public void onStatistiqueLoadFailed(String messageError) {
        message.setValue(messageError);
    }
}
