package fr.android.tennistrackerv2.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.Model.Match;
import fr.android.tennistrackerv2.Model.Statistique;
import fr.android.tennistrackerv2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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



    // TODO: Rename and change types of parameters
    private Club club1;
    private Club club2;
    public Statistique stats1;
    public Statistique stats2;
    public Match match;


    public MatchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance(Club club1, Club club2, Statistique statistique) {
        MatchFragment fragment = new MatchFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("club1", club1);
        bundle.putSerializable("club2", club2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        club1 = (Club) getArguments().getSerializable("club1");
        club2 = (Club) getArguments().getSerializable("club2");

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
            txtScoreClub1.setText(""+scoreTeamA);
            stats1.setTir(2);
        });

        btnGoalB.setOnClickListener(view1 -> {
            scoreTeamB++;
            txtScoreClub2.setText(""+scoreTeamB);
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

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String strDate = dateFormat.format(date).toString();
        match = new Match(club1, club2, strDate, "Stade de France");
        return view;
    }

    public Club getClub1() {
        return club1;
    }

    public Match getMatch() {
        return match;
    }

    public void setStats1(Statistique stats) {
        this.stats1 = stats;
    }


    public void setTirA(int tir) {
        this.tir = tir;
    }

    public int getTirA() {
        return this.tir;
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
                    this.setTirA(tir);
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
        final String [] langs = {"Faute","Carton jaune", "Carton rouge", "Carton jaune + Penalty", "Carton rouge + Penalty"};
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

}