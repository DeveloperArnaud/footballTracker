package fr.android.tennistrackerv2.Callback;

import java.util.List;

import fr.android.tennistrackerv2.Model.Match;

public interface IMatchCallbackListener {


    void onMatchLoadSuccess(List<Match> matchList);
    void onMatchLoadFailed(String message);
}
