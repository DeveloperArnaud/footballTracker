package fr.android.tennistrackerv2.Callback;

import java.util.List;

import fr.android.tennistrackerv2.Model.Upload;

public interface IUploadCallbackListener {

    void onUploadLoadSuccess(List<Upload> uploadList);
    void onUploadLoadFailed(String message);
}
