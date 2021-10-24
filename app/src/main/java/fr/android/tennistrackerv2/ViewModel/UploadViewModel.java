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

import fr.android.tennistrackerv2.Callback.IUploadCallbackListener;
import fr.android.tennistrackerv2.Common;
import fr.android.tennistrackerv2.Model.Upload;

public class UploadViewModel extends ViewModel implements IUploadCallbackListener {

    private MutableLiveData<List<Upload>> uploadList;
    private MutableLiveData<String> message;
    public IUploadCallbackListener listener;

    public UploadViewModel() {
        listener = this;
    }

    public MutableLiveData<List<Upload>> getUploadList() {
        if(uploadList == null) {
            uploadList = new MutableLiveData<>();
            message = new MutableLiveData<>();
            loadUploadList();
        }
        return uploadList;
    }

    private void loadUploadList() {
        List<Upload> uploads = new ArrayList<>();
        DatabaseReference pictureRef = FirebaseDatabase.getInstance().getReference(Common.PICTURE_REF);
        pictureRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Upload upload = dataSnapshot.getValue(Upload.class);
                    uploads.add(upload);
                }
                listener.onUploadLoadSuccess(uploads);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onUploadLoadFailed(error.getMessage());
            }
        });
    }


    @Override
    public void onUploadLoadSuccess(List<Upload> uploads) {
        uploadList.setValue(uploads);
    }

    @Override
    public void onUploadLoadFailed(String messageError) {
        message.setValue(messageError);
    }
}
