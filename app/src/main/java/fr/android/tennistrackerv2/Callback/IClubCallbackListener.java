package fr.android.tennistrackerv2.Callback;

import java.util.List;

import fr.android.tennistrackerv2.Model.Club;

public interface IClubCallbackListener {

    void onClubLoadSuccess(List<Club> clubList);
    void onClubLoadFailed(String messageError);

}
