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
import fr.android.tennistrackerv2.Common;
import fr.android.tennistrackerv2.Model.Format;

public class FormatViewModel extends ViewModel implements IFormatCallbackListener {

    private MutableLiveData<List<Format>> formatList;
    private MutableLiveData<String> message;
    public IFormatCallbackListener listener;

    public FormatViewModel() {
        listener = this;
    }

    public MutableLiveData<List<Format>> getFormatList() {
        if(formatList ==null) {
            formatList = new MutableLiveData<>();
            message = new MutableLiveData<>();
            loadFormatList();
        }

        return formatList;
    }

    private void loadFormatList() {
            List<Format> formats = new ArrayList<>();
            DatabaseReference formatRef = FirebaseDatabase.getInstance().getReference(Common.FORMAT_REF);
        formatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Format format = dataSnapshot.getValue(Format.class);
                    formats.add(format);
                }
                listener.onFormatLoadSuccess(formats);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onFormatLoadFailed(error.getMessage());
            }
        });
    }


    @Override
    public void onFormatLoadSuccess(List<Format> formats) {
        formatList.setValue(formats);
    }

    @Override
    public void onFormatLoadFailed(String messageError) {
        message.setValue(messageError);
    }
}
