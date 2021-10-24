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

import fr.android.tennistrackerv2.Callback.ISendDataFragment;
import fr.android.tennistrackerv2.Model.Club;
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
    private TextView txtScoreClub2;
    private TextView cartonJATxt;
    private TextView cartonRATxt;
    private TextView tirsTxt;
    private TextView tirCadreTxt;
    private ImageView imgView_logo_club1;
    private ImageView imgView_logo_club2;
    Button button;
    Button button2;
    Button btnFauteA;
    Button btnTirA;
    int scoreTeamA = 0;
    int scoreTeamB = 0;
    int fauteA = 0;
    int cartonJauneA = 0;
    int cartonRougeA = 0;
    int tir = 0;
    int tirCadre = 0;


    // TODO: Rename and change types of parameters
    private Club club1;
    private Club club2;

    public MatchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatchFragment newInstance(Club club1, Club club2) {
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
        System.out.println(club2);
        txtNameClub1 = view.findViewById(R.id.txtNameClub1);
        txtNameClub2 = view.findViewById(R.id.txtNameClub2);
        imgView_logo_club1 = view.findViewById(R.id.imgView_logo_club1);
        imgView_logo_club2 = view.findViewById(R.id.imgView_logo_club2);
        txtNameClub1.setText(clubNameTooLong(club1.getName()));
        txtNameClub2.setText(clubNameTooLong(club2.getName()));
        button = view.findViewById(R.id.btnGoalA);
        button2 = view.findViewById(R.id.btnGoalB);
        btnFauteA = view.findViewById(R.id.btnFauteA);
        btnTirA = view.findViewById(R.id.btnTirA);
        txtScoreClub1 = view.findViewById(R.id.txtScoreClub1);
        txtScoreClub2 = view.findViewById(R.id.txtScoreClub2);
        fautesATxt = view.findViewById(R.id.fautesATxt);
        cartonJATxt = view.findViewById(R.id.cartonJATxt);
        cartonRATxt = view.findViewById(R.id.cartonRATxt);
        tirsTxt = view.findViewById(R.id.tirsTxt);
        tirCadreTxt = view.findViewById(R.id.tirsCadresTxt);
        fautesATxt.setText("");
        cartonJATxt.setText("");
        cartonRATxt.setText("");
        tirsTxt.setText("");
        tirCadreTxt.setText("");


        button.setOnClickListener(view1 -> {
            scoreTeamA++;
            txtScoreClub1.setText(""+scoreTeamA);
        });

        button2.setOnClickListener(view1 -> {
            scoreTeamB++;
            txtScoreClub2.setText(""+scoreTeamB);
        });

        btnFauteA.setOnClickListener(view12 -> {
            showDialogClubs(view12);

        });

        btnTirA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogTirs(view);
            }
        });
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

        return view;
    }

    private void showDialogTirs(View
                                         view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(getResources().getString(R.string.dialog_serve_player));
        final String [] langs = {"Tir", "Tir cadré"};
        builder.setSingleChoiceItems(langs, -1, (dialogInterface, i) -> {
            if(i == 0) {
                tir++;
                tirsTxt.setText("Tir(s) : "+tir);
            } else {
                tirCadre ++;
                tir++;
                tirsTxt.setText("Tir(s) : "+tir);
                tirCadreTxt.setText("Tir(s) Cadré(s) : "+tirCadre);
            }
            dialogInterface.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    private void showDialogClubs(View
                                 view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(getResources().getString(R.string.dialog_serve_player));
        final String [] langs = {"Faute","Carton jaune", "Carton rouge", "Carton jaune + Penalty", "Carton rouge + Penalty"};
        builder.setSingleChoiceItems(langs, -1, (dialogInterface, i) -> {
            if(i == 0) {
                fauteA++;
                fautesATxt.setText("Faute(s) : "+fauteA);
            } else if(i == 1) {
                fauteA++;
                cartonJauneA ++;
                cartonJATxt.setText("Carton(s) jaune(s) : " + cartonJauneA);
                fautesATxt.setText("Faute(s) : "+fauteA);
            } else if(i == 2) {
                fauteA++;
                cartonRougeA ++;
                cartonRATxt.setText("Carton(s) rouge(s) : " + cartonRougeA);
                fautesATxt.setText("Faute(s) : "+fauteA);
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