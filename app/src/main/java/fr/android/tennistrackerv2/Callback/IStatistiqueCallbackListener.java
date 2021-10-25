package fr.android.tennistrackerv2.Callback;

import java.util.List;

import fr.android.tennistrackerv2.Model.Statistique;

public interface IStatistiqueCallbackListener {


    void onStatistiqueLoadSuccess(List<Statistique> statistiqueList);
    void onStatistiqueLoadFailed(String message);
}
