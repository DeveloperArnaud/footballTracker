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

import fr.android.tennistrackerv2.Callback.IFormatCallbackListener;
import fr.android.tennistrackerv2.Callback.IFormatLastMatchCallbackListener;
import fr.android.tennistrackerv2.Common;
import fr.android.tennistrackerv2.Model.Format;
import fr.android.tennistrackerv2.Model.FormatLastMatch;

public class FormatLastMatchViewModel extends ViewModel implements IFormatLastMatchCallbackListener {

    private MutableLiveData<List<FormatLastMatch>> formatLastMatchesList;
    private MutableLiveData<String> message;
    public IFormatLastMatchCallbackListener listener;

    public FormatLastMatchViewModel() {
        listener = this;
    }

    public MutableLiveData<List<FormatLastMatch>> getFormatLastMatchList() {
        if(formatLastMatchesList ==null) {
            formatLastMatchesList = new MutableLiveData<>();
            message = new MutableLiveData<>();
            loadFormatLastMatchList();
        }

        return formatLastMatchesList;
    }

    private void loadFormatLastMatchList() {
        List<FormatLastMatch> formatLastMatches = new ArrayList<>();
        DatabaseReference formatRef = FirebaseDatabase.getInstance().getReference(Common.FORMAT_LAST_MATCH_REF);
        formatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FormatLastMatch formatLastMatch = dataSnapshot.getValue(FormatLastMatch.class);
                    formatLastMatches.add(formatLastMatch);
                }
                listener.onFormatLastMatchLoadSuccess(formatLastMatches);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onFormatLastMatchLoadFailed(error.getMessage());
            }
        });
    }

    @Override
    public void onFormatLastMatchLoadSuccess(List<FormatLastMatch> formatLastMatches) {
        formatLastMatchesList.setValue(formatLastMatches);
    }

    @Override
    public void onFormatLastMatchLoadFailed(String messageError) {
        message.setValue(messageError);
    }
}
