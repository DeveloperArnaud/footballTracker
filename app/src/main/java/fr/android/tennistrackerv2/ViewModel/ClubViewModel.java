package fr.android.tennistrackerv2.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import fr.android.tennistrackerv2.Callback.IClubCallbackListener;
import fr.android.tennistrackerv2.Common;
import fr.android.tennistrackerv2.Model.Club;

public class ClubViewModel extends ViewModel implements IClubCallbackListener {

    private MutableLiveData<List<Club>> clubData;
    private MutableLiveData<String> message;
    public IClubCallbackListener listener;


    public ClubViewModel() {
        listener = this;
    }

    public MutableLiveData<List<Club>> getClubData() {
        if(clubData == null) {
            clubData = new MutableLiveData<>();
            message = new MutableLiveData<>();
            loadClubData();
        }

        return clubData;
    }

    private void loadClubData() {
        List<Club> clubs = new ArrayList<>();
        DatabaseReference clubRef = FirebaseDatabase.getInstance().getReference(Common.CLUB_REF);
        clubRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Club club = dataSnapshot.getValue(Club.class);
                    clubs.add(club);
                }

                listener.onClubLoadSuccess(clubs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onClubLoadFailed(error.getMessage());
            }
        });
    }

    @Override
    public void onClubLoadSuccess(List<Club> clubList) {
        clubData.setValue(clubList);
    }

    @Override
    public void onClubLoadFailed(String messageError) {
        message.setValue(messageError);
    }
}
