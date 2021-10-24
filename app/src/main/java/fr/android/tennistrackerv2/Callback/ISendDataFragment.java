package fr.android.tennistrackerv2.Callback;

import java.util.List;

import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.Model.Upload;

public interface ISendDataFragment {

    void onDataReceived(List<Upload> uploadList);
}
