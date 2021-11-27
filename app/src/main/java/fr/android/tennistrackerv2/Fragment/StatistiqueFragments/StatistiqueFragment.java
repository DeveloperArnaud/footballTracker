package fr.android.tennistrackerv2.Fragment.StatistiqueFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import fr.android.tennistrackerv2.Model.Match;
import fr.android.tennistrackerv2.R;
import fr.android.tennistrackerv2.Utils.Utils;

public class StatistiqueFragment extends Fragment {

    private ImageView imgView_logo_club1;
    private ImageView imgView_logo_clubA;
    private TextView txtNameClub1;
    private TextView txtScoreClub1;
    private TextView tirsTxtA;
    private TextView tirCadreTxtA;
    private TextView passeTxtA;
    private TextView fautesTxtA;
    private TextView cartonJTxtA;
    private TextView cartonRTxtA;
    private TextView horsJeuTxtA;

    private ImageView imgView_logo_club2;
    private ImageView imgView_logo_clubB;
    private TextView txtNameClub2;
    private TextView txtScoreClub2;
    private TextView tirsTxtB;
    private TextView tirCadreTxtB;
    private TextView passeTxtB;
    private TextView fautesTxtB;
    private TextView cartonJTxtB;
    private TextView cartonRTxtB;
    private TextView horsJeuTxtB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistique_fragment, container, false);


        imgView_logo_club1 = view.findViewById(R.id.imgView_logo_club1);
        imgView_logo_clubA = view.findViewById(R.id.imgView_logo_clubA);
        txtNameClub1 = view.findViewById(R.id.txtNameClub1);
        txtScoreClub1 = view.findViewById(R.id.txtScoreClub1);
        tirsTxtA = view.findViewById(R.id.tirsTxtA);
        tirCadreTxtA = view.findViewById(R.id.tirsCadresTxtA);
        passeTxtA = view.findViewById(R.id.passesTxtA);
        fautesTxtA = view.findViewById(R.id.fautesA);
        cartonJTxtA = view.findViewById(R.id.cartonJTxtA);
        cartonRTxtA = view.findViewById(R.id.cartonRTxtA);
        horsJeuTxtA = view.findViewById(R.id.horsJeuTxtA);

        imgView_logo_club2 = view.findViewById(R.id.imgView_logo_club2);
        imgView_logo_clubB = view.findViewById(R.id.imgView_logo_clubB);
        txtNameClub2 = view.findViewById(R.id.txtNameClub2);
        txtScoreClub2 = view.findViewById(R.id.txtScoreClub2);
        tirsTxtB = view.findViewById(R.id.tirsTxtB);
        tirCadreTxtB = view.findViewById(R.id.tirsCadresTxtB);
        passeTxtB = view.findViewById(R.id.passesTxtB);
        fautesTxtB = view.findViewById(R.id.fautesB);
        cartonJTxtB = view.findViewById(R.id.cartonJTxtB);
        cartonRTxtB = view.findViewById(R.id.cartonRTxtB);
        horsJeuTxtB = view.findViewById(R.id.horsJeuTxtB);

        //Intent stats = getIntent();
        Bundle matchStatsBundle = getArguments();
        Match matchStats = (Match) matchStatsBundle.getSerializable("match");

        Picasso.with(getActivity())
                .load((String) matchStats.getClub1().getImgUrl())
                .fit()
                .centerCrop()
                .into(imgView_logo_club1);
        Picasso.with(getActivity())
                .load(matchStats.getClub1().getImgUrl())
                .fit()
                .centerCrop()
                .into(imgView_logo_clubA);

        txtNameClub1.setText(Utils.clubNameTooLong(matchStats.getClub1().getName()));
        txtScoreClub1.setText(String.valueOf(matchStats.getClub1().getStatistique().getScore()));
        tirsTxtA.setText(String.valueOf(matchStats.getClub1().getStatistique().getTir()));
        tirCadreTxtA.setText(String.valueOf(matchStats.getClub1().getStatistique().getTirCadre()));
        passeTxtA.setText(String.valueOf(matchStats.getClub1().getStatistique().getPasse()));
        fautesTxtA.setText(String.valueOf(matchStats.getClub1().getStatistique().getFautes()));
        cartonJTxtA.setText(String.valueOf(matchStats.getClub1().getStatistique().getCartonJaune()));
        cartonRTxtA.setText(String.valueOf(matchStats.getClub1().getStatistique().getCartonRouge()));
        horsJeuTxtA.setText(String.valueOf(matchStats.getClub1().getStatistique().getHorsJeu()));

        Picasso.with(getActivity())
                .load(matchStats.getClub2().getImgUrl())
                .fit()
                .centerCrop()
                .into(imgView_logo_club2);
        Picasso.with(getActivity())
                .load(matchStats.getClub2().getImgUrl())
                .fit()
                .centerCrop()
                .into(imgView_logo_clubB);

        txtNameClub2.setText(Utils.clubNameTooLong(matchStats.getClub2().getName()));
        txtScoreClub2.setText(String.valueOf(matchStats.getClub2().getStatistique().getScore()));
        tirsTxtB.setText(String.valueOf(matchStats.getClub2().getStatistique().getTir()));
        tirCadreTxtB.setText(String.valueOf(matchStats.getClub2().getStatistique().getTirCadre()));
        passeTxtB.setText(String.valueOf(matchStats.getClub2().getStatistique().getPasse()));
        fautesTxtB.setText(String.valueOf(matchStats.getClub2().getStatistique().getFautes()));
        cartonJTxtB.setText(String.valueOf(matchStats.getClub2().getStatistique().getCartonJaune()));
        cartonRTxtB.setText(String.valueOf(matchStats.getClub2().getStatistique().getCartonRouge()));
        horsJeuTxtB.setText(String.valueOf(matchStats.getClub2().getStatistique().getHorsJeu()));

        return view;
    }

    

}
