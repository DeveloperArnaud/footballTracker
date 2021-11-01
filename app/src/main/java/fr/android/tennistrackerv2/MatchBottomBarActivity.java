package fr.android.tennistrackerv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.android.tennistrackerv2.Fragment.MatchFragment;
import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.Model.ClubStats;
import fr.android.tennistrackerv2.Model.Match;
import fr.android.tennistrackerv2.Model.Statistique;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MatchBottomBarActivity extends AppCompatActivity {

    private Club club1;
    private Club club2;

    private Statistique stats1;
    private Statistique stats2;

    private Match match;

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

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date date = new Date();
    private final String strDate = dateFormat.format(date).toString();

    private String serialId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_bottom_bar);
        Intent intent = getIntent();
        club1 = (Club) intent.getSerializableExtra("club1");
        club2 = (Club) intent.getSerializableExtra("club2");
        txtNameClub1 = findViewById(R.id.txtNameClub1);
        txtNameClub2 = findViewById(R.id.txtNameClub2);
        imgView_logo_club1 = findViewById(R.id.imgView_logo_club1);
        imgView_logo_club2 =findViewById(R.id.imgView_logo_club2);
        txtNameClub1.setText(clubNameTooLong(club1.getName()));
        txtNameClub2.setText(clubNameTooLong(club2.getName()));
        btnGoalA = findViewById(R.id.btnGoalA);
        btnGoalB = findViewById(R.id.btnGoalB);
        btnFauteA = findViewById(R.id.btnFauteA);
        btnFauteB = findViewById(R.id.btnFauteB);
        btnTirA = findViewById(R.id.btnTirA);
        btnTirB = findViewById(R.id.btnTirB);
        btnPasseA = findViewById(R.id.btnPasseA);
        btnPasseB = findViewById(R.id.btnPasseB);
        btnHorsJeuA = findViewById(R.id.btnHorsJeuA);
        btnHorsJeuB = findViewById(R.id.btnHorsJeuB);
        txtScoreClub1 = findViewById(R.id.txtScoreClub1);
        txtScoreClub2 = findViewById(R.id.txtScoreClub2);
        fautesATxt = findViewById(R.id.fautesATxt);
        fautesTxtB = findViewById(R.id.fautesTxtB);
        cartonJATxt = findViewById(R.id.cartonJATxt);
        cartonJTxtB = findViewById(R.id.cartonJTxtB);
        cartonRATxt = findViewById(R.id.cartonRATxt);
        cartonRTxtB = findViewById(R.id.cartonRTxtB);
        passeTxtA = findViewById(R.id.passeTxtA);
        passeTxtB = findViewById(R.id.passeTxtB);
        tirsTxt = findViewById(R.id.tirsTxt);
        tirsTxtB = findViewById(R.id.tirsTxtB);
        tirCadreTxt = findViewById(R.id.tirsCadresTxt);
        tirCadreTxtB = findViewById(R.id.tirsCadresTxtB);
        horsJeuTxtA = findViewById(R.id.horsJeuTxtA);
        horsJeuTxtB = findViewById(R.id.horsJeuTxtB);
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

        serialId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Match");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.matchBoard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.matchBoard:
                        return true;
                    case R.id.localisationPage:
                        startActivity(new Intent(getApplicationContext(), LocationActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.picturePage:
                        startActivity(new Intent(getApplicationContext(), PictureActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });



        btnTirB.setOnClickListener(view13 -> showDialogTirs(view13,2));
        Picasso.with(this)
                .load(club1.getImgUrl())
                .fit()
                .centerCrop()
                .into(imgView_logo_club1);
        Picasso.with(this)
                .load(club2.getImgUrl())
                .fit()
                .centerCrop()
                .into(imgView_logo_club2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(this, UploadActivity.class);
        if(requestCode == 100 && data != null) {
            Bundle bundle = data.getExtras();
            Uri img = data.getData();
            if(bundle != null) {
                Bitmap photo = (Bitmap) bundle.get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_picture) {// User chose the "Settings" item, show the app settings UI...
            if(ContextCompat.checkSelfPermission(MatchBottomBarActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ;
                ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.CAMERA
                }, 100);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);
            return true;
        }

        else if(item.getItemId() == R.id.action_done) {
            stats1 = new Statistique( tir, tirCadre, scoreTeamA, fauteA, cartonJauneA, cartonRougeA, passeA, horsJeuA, 0);
            stats2 = new Statistique(tirB, tirCadreB, scoreTeamB, fauteB, cartonJauneB, cartonRougeB, passeB, horsJeuB, 0 );
            ClubStats clubStats1 = new ClubStats(club1.getName(), club1.getImgUrl(), club1.getImgUrl96(), stats1);
            ClubStats clubStats2 = new ClubStats(club2.getName(), club2.getImgUrl(), club2.getImgUrl96(), stats2);
            match = new Match(serialId, clubStats1, clubStats2,strDate,"8 Impasse Eugene Ducretet");
            DatabaseReference matchRef = FirebaseDatabase.getInstance().getReference("Match");
            matchRef.push().setValue(match);
            showDialogDoneMatch();
        }

        // If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
    }

    private void showDialogDoneMatch() {
        String messageCancelMatch = getResources().getString(R.string.dialog_cancel_match);
        String btnYesString = getResources().getString(R.string.yes_txt_btn);
        String btnNoString = getResources().getString(R.string.no_txt_btn);
        new AlertDialog.Builder(this)
                .setMessage("Match terminée")
                .setPositiveButton(btnYesString, (dialogInterface, i) -> {

                    finish();
                    Intent intent = new Intent(this, HistoryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("serialId", serialId);
                    startActivity(intent);
                })
                .setNegativeButton(btnNoString, null)
                .show();

    }

    private void showDialogCancelMatch() {
        String messageCancelMatch = getResources().getString(R.string.dialog_cancel_match);
        String btnYesString = getResources().getString(R.string.yes_txt_btn);
        String btnNoString = getResources().getString(R.string.no_txt_btn);
        new AlertDialog.Builder(this)
                .setMessage(messageCancelMatch)
                .setPositiveButton(btnYesString, (dialogInterface, i) -> {
                    finish();
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                })
                .setNegativeButton(btnNoString, null)
                .show();

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
    public void onBackPressed() {
        showDialogCancelMatch();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
    }
}