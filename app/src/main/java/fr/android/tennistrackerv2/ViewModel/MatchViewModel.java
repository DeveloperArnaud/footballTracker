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

import fr.android.tennistrackerv2.Callback.IMatchCallbackListener;
import fr.android.tennistrackerv2.Model.Match;

public class MatchViewModel extends ViewModel implements IMatchCallbackListener{

    private MutableLiveData<List<Match>> match;
    private MutableLiveData<String> message;
    public IMatchCallbackListener listener;


    public MatchViewModel() {
        listener = this;
    }

    public MutableLiveData<List<Match>> getMatches(String serialId) {
        if(match == null) {
            match = new MutableLiveData<>();
            message = new MutableLiveData<>();
            loadMatchesData(serialId);
        }

        return match;
    }

    private void loadMatchesData(String serialId) {
        List<Match> matches = new ArrayList<>();
        DatabaseReference stats = FirebaseDatabase.getInstance().getReference("Match");
        Query checkSerialId = stats.orderByChild("serialId").equalTo(serialId);
        checkSerialId.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Match match = dataSnapshot.getValue(Match.class);
                    matches.add(match);
                }
                listener.onMatchLoadSuccess(matches);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onMatchLoadFailed(error.getMessage());
            }
        });
    }


    @Override
    public void onMatchLoadSuccess(List<Match> matchList) {
        match.setValue(matchList);
    }

    @Override
    public void onMatchLoadFailed(String messageError) {
        message.setValue(messageError);
    }

}
