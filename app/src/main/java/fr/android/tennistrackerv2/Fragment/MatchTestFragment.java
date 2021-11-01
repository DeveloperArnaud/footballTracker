package fr.android.tennistrackerv2.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.android.tennistrackerv2.HistoryActivity;
import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.Model.ClubStats;
import fr.android.tennistrackerv2.Model.Match;
import fr.android.tennistrackerv2.Model.Statistique;
import fr.android.tennistrackerv2.R;

public class MatchTestFragment extends Fragment {

    //Match
    private TextView txtNameClub1;
    private TextView txtNameClub2;
    private TextView txtScoreClub1;
    private TextView fautesATxt;
    private TextView cartonJATxt;
    private TextView cartonRATxt;
    private TextView tirsTxt;
    private TextView tirCadreTxt;
    private TextView passeTxtA;
    private TextView horsJeuTxtA;

    private Statistique stats1;
    private Statistique stats2;

    private Match match;

    private String serialId;

    private TextView txtScoreClub2;
    private TextView fautesTxtB;
    private TextView cartonJTxtB;
    private TextView cartonRTxtB;
    private TextView tirsTxtB;
    private TextView tirCadreTxtB;
    private TextView passeTxtB;
    private TextView horsJeuTxtB;

    private ImageView imgView_logo_club1;
    private ImageView imgView_logo_club2;
    Button btnGoalA;
    Button btnGoalB;
    Button btnFauteA;
    Button btnTirA;
    Button btnHorsJeuA;
    Button btnHorsJeuB;
    Button btnFauteB;
    Button btnTirB;
    Button btnPasseA;
    Button btnPasseB;
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int fauteA = 0;
    int fauteB = 0;
    int cartonJauneA = 0;
    int cartonJauneB = 0;
    int cartonRougeA = 0;
    int cartonRougeB = 0;
    int tir = 0;
    int tirB = 0;
    int tirCadre = 0;
    int tirCadreB = 0;
    int passeA = 0;
    int passeB = 0;
    int horsJeuA = 0;
    int horsJeuB = 0;


    FloatingActionButton fab_btn;
    Bundle bundleClub;
    Club club1;
    Club club2;

    String address;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date = new Date();
    private final String strDate = dateFormat.format(date);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_test, container, false);
        bundleClub = getArguments();
        serialId = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
        club1 = (Club) bundleClub.getSerializable("club1");
        club2 = (Club) bundleClub.getSerializable("club2");
        address = bundleClub.getString("address");

        txtNameClub1 = view.findViewById(R.id.txtNameClub1);
        txtNameClub2 = view.findViewById(R.id.txtNameClub2);
        imgView_logo_club1 = view.findViewById(R.id.imgView_logo_club1);
        imgView_logo_club2 = view.findViewById(R.id.imgView_logo_club2);
        txtNameClub1.setText(clubNameTooLong(club1.getName()));
        txtNameClub2.setText(clubNameTooLong(club2.getName()));
        btnGoalA = view.findViewById(R.id.btnGoalA);
        btnGoalB = view.findViewById(R.id.btnGoalB);
        btnFauteA = view.findViewById(R.id.btnFauteA);
        btnFauteB = view.findViewById(R.id.btnFauteB);
        btnTirA = view.findViewById(R.id.btnTirA);
        btnTirB = view.findViewById(R.id.btnTirB);
        btnPasseA = view.findViewById(R.id.btnPasseA);
        btnPasseB = view.findViewById(R.id.btnPasseB);
        btnHorsJeuA = view.findViewById(R.id.btnHorsJeuA);
        btnHorsJeuB = view.findViewById(R.id.btnHorsJeuB);
        txtScoreClub1 = view.findViewById(R.id.txtScoreClub1);
        txtScoreClub2 = view.findViewById(R.id.txtScoreClub2);
        fautesATxt = view.findViewById(R.id.fautesATxt);
        fautesTxtB = view.findViewById(R.id.fautesTxtB);
        cartonJATxt = view.findViewById(R.id.cartonJATxt);
        cartonJTxtB = view.findViewById(R.id.cartonJTxtB);
        cartonRATxt = view.findViewById(R.id.cartonRATxt);
        cartonRTxtB = view.findViewById(R.id.cartonRTxtB);
        passeTxtA = view.findViewById(R.id.passeTxtA);
        passeTxtB = view.findViewById(R.id.passeTxtB);
        tirsTxt = view.findViewById(R.id.tirsTxt);
        tirsTxtB = view.findViewById(R.id.tirsTxtB);
        tirCadreTxt = view.findViewById(R.id.tirsCadresTxt);
        tirCadreTxtB = view.findViewById(R.id.tirsCadresTxtB);
        horsJeuTxtA = view.findViewById(R.id.horsJeuTxtA);
        horsJeuTxtB = view.findViewById(R.id.horsJeuTxtB);
        fautesATxt.setText("");
        cartonJATxt.setText("");
        cartonRATxt.setText("");
        tirsTxt.setText("");
        tirCadreTxt.setText("");
        fautesTxtB.setText("");
        cartonJTxtB.setText("");
        cartonRTxtB.setText("");
        tirsTxtB.setText("");
        tirCadreTxtB.setText("");
        passeTxtA.setText("");
        passeTxtB.setText("");
        horsJeuTxtA.setText("");
        horsJeuTxtB.setText("");


        btnGoalA.setOnClickListener(view1 -> {
            scoreTeamA++;
            tir++;
            tirCadre++;
            txtScoreClub1.setText(""+scoreTeamA);
            tirsTxt.setText("Tir(s) : "+tir);
            tirCadreTxt.setText("Tir(s) Cadré(s) : " + tirCadre);
        });

        btnGoalB.setOnClickListener(view1 -> {
            scoreTeamB++;
            tirB++;
            tirCadreB++;
            txtScoreClub2.setText(""+scoreTeamB);
            tirsTxtB.setText("Tir(s) : " + tirB);
            tirCadreTxtB.setText("Tir(s) Cadré(s) : " + tirCadreB);
        });

        btnPasseA.setOnClickListener(view1 -> {
            passeA++;
            passeTxtA.setText("Passe(s) : "+passeA);
        });

        btnPasseB.setOnClickListener(view1 -> {
            passeB++;
            passeTxtB.setText("Passe(s) : "+passeB);
        });

        btnHorsJeuA.setOnClickListener(view1 -> {
            horsJeuA++;
            horsJeuTxtA.setText("Hors-jeu : "+horsJeuA);
        });

        btnHorsJeuB.setOnClickListener(view1 -> {
            horsJeuB++;
            horsJeuTxtB.setText("Hors-jeu : "+horsJeuB);
        });

        btnFauteA.setOnClickListener(view12 -> {
            showDialogClubs(view12, 1);

        });

        btnFauteB.setOnClickListener(view12 -> {
            showDialogClubs(view12, 2);

        });

        btnTirA.setOnClickListener(view14 -> showDialogTirs(view14,1)

        );

        btnTirB.setOnClickListener(view13 -> showDialogTirs(view13,2));

        Picasso.with(getContext())
                .load(club1.getImgUrl())
                .fit()
                .centerCrop()
                .into(imgView_logo_club1);
        Picasso.with(getContext())
                .load(club2.getImgUrl())
                .fit()
                .centerCrop()
                .into(imgView_logo_club2);



        fab_btn  = view.findViewById(R.id.fab_btn);
        fab_btn.setOnClickListener(view15 -> {
            stats1 = new Statistique( tir, tirCadre, scoreTeamA, fauteA, cartonJauneA, cartonRougeA, passeA, horsJeuA, 0);
            stats2 = new Statistique(tirB, tirCadreB, scoreTeamB, fauteB, cartonJauneB, cartonRougeB, passeB, horsJeuB, 0 );
            ClubStats clubStats1 = new ClubStats(club1.getName(), club1.getImgUrl(), club1.getImgUrl96(), stats1);
            ClubStats clubStats2 = new ClubStats(club2.getName(), club2.getImgUrl(), club2.getImgUrl96(), stats2);
            match = new Match(serialId, clubStats1, clubStats2,strDate,address);
            DatabaseReference matchRef = FirebaseDatabase.getInstance().getReference("Match");
            matchRef.push().setValue(match);
            showDialogDoneMatch();
        });

        return view;

    }

    private void showDialogTirs(View view, int team) {

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(getResources().getString(R.string.dialog_tir));
        final String [] langs = {"Tir", "Tir cadré"};
        builder.setSingleChoiceItems(langs, -1, (dialogInterface, i) -> {
            if(i == 0) {
                if(team == 1) {
                    tir++;
                    tirsTxt.setText("Tir(s) : "+tir);
                } else {
                    tirB++;
                    tirsTxtB.setText("Tir(s) : " + tirB);
                }
            } else {
                if(team == 1) {
                    tirCadre++;
                    tir++;
                    tirsTxt.setText("Tir(s) : " + tir);
                    tirCadreTxt.setText("Tir(s) Cadré(s) : " + tirCadre);
                } else {
                    tirCadreB++;
                    tirB++;
                    tirsTxtB.setText("Tir(s) : " + tirB);
                    tirCadreTxtB.setText("Tir(s) Cadré(s) : " + tirCadreB);
                }
            }

            dialogInterface.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    private void showDialogClubs(View view, int team) {

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(getResources().getString(R.string.dialog_faute));
        final String [] langs = {"Faute","Carton jaune", "Carton rouge"};
        builder.setSingleChoiceItems(langs, -1, (dialogInterface, i) -> {
            if(i == 0) {
                if(team == 1) {
                    fauteA++;
                    fautesATxt.setText("Faute(s) : " + fauteA);
                } else {
                    fauteB++;
                    fautesTxtB.setText("Faute(s) : " + fauteB);
                }
            } else if(i == 1) {
                if(team == 1) {
                    fauteA++;
                    cartonJauneA++;
                    cartonJATxt.setText("Carton(s) jaune(s) : " + cartonJauneA);
                    fautesATxt.setText("Faute(s) : " + fauteA);
                } else {
                    fauteB++;
                    cartonJauneB++;
                    cartonJTxtB.setText("Carton(s) jaune(s) : " + cartonJauneB);
                    fautesTxtB.setText("Faute(s) : " + fauteB);
                }
            } else if(i == 2) {
                if(team == 1) {
                    fauteA++;
                    cartonRougeA++;
                    cartonRATxt.setText("Carton(s) rouge(s) : " + cartonRougeA);
                    fautesATxt.setText("Faute(s) : " + fauteA);
                } else {
                    fauteB++;
                    cartonRougeB++;
                    cartonRTxtB.setText("Carton(s) rouge(s) : " + cartonRougeB);
                    fautesTxtB.setText("Faute(s) : " + fauteB);
                }
            } else {

            }
            dialogInterface.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    public String clubNameTooLong(String clubName) {
        String outString = "";
        if(clubName.trim().length() >= 10) {
            for(int i = 0; i < clubName.length(); i++) {
                char c = clubName.charAt(i);
                outString += (Character.isUpperCase(c) ? c + " " : "").trim();
            }
        } else {
            return clubName;
        }
        return outString;
    }

    private void showDialogDoneMatch() {
        String messageCancelMatch = getResources().getString(R.string.dialog_cancel_match);
        String btnYesString = getResources().getString(R.string.yes_txt_btn);
        String btnNoString = getResources().getString(R.string.no_txt_btn);
        new AlertDialog.Builder(getActivity())
                .setMessage("Match terminée")
                .setPositiveButton(btnYesString, (dialogInterface, i) -> {
                    getActivity().finish();
                    Intent intent = new Intent(getActivity(), HistoryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("serialId", serialId);
                    startActivity(intent);
                })
                .setNegativeButton(btnNoString, null)
                .show();

    }

}
