package fr.android.tennistrackerv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import fr.android.tennistrackerv2.Callback.ISendDataFragment;
import fr.android.tennistrackerv2.Fragment.Adapter.FragmentAdapter;
import fr.android.tennistrackerv2.Fragment.MatchFragment;
import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.Model.Match;
import fr.android.tennistrackerv2.Model.Upload;

public class MatchActivity2 extends AppCompatActivity implements ISendDataFragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter fragmentAdapter;
    String txtMatch;
    String txtLocation;
    String txtPictures;
    Club club1;
    Club club2;
    String play1String;
    String play2String;
    ISendDataFragment sendDataFragment;
    DatabaseReference matchRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match2);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Intent i = getIntent();
        club1 = (Club) i.getSerializableExtra("club1");
        club2 = (Club) i.getSerializableExtra("club2");
        showDialogClubs(club1, club2);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewPager2);
        //txtMatch = getResources().getString();
        //txtLocation = getResources().getString();
        //txtPictures = getResources().getString();


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentAdapter = new FragmentAdapter(fragmentManager, getLifecycle(), club1, club2);
        viewPager2.setAdapter(fragmentAdapter);
        tabLayout.addTab(tabLayout.newTab().setText("Match"));
        tabLayout.addTab(tabLayout.newTab().setText("Location"));
        tabLayout.addTab(tabLayout.newTab().setText("Pictures"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));

            }
        });

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
            if(ContextCompat.checkSelfPermission(MatchActivity2.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ;
                ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.CAMERA
                }, 100);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);


            return true;
        } else if(item.getItemId() == R.id.action_done) {
            matchRef = FirebaseDatabase.getInstance().getReference().child("Match");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String strDate = dateFormat.format(date).toString();
            System.out.println(fragmentAdapter.getUploads());
            Match match = new Match(club1, club2, strDate, "8 Impasse Eugene Ducretet", fragmentAdapter.getUploads());
            matchRef.push().setValue(match);
            Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
            showDialogDoneMatch();
        }
        // If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
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


    private void showDialogCancelMatch() {
        String messageCancelMatch = getResources().getString(R.string.dialog_cancel_match);
        String btnYesString = getResources().getString(R.string.yes_txt_btn);
        String btnNoString = getResources().getString(R.string.no_txt_btn);
        new AlertDialog.Builder(this)
                .setMessage(messageCancelMatch)
                .setPositiveButton(btnYesString, (dialogInterface, i) -> finish())
                .setNegativeButton(btnNoString, null)
                .show();

    }

    private void showDialogDoneMatch() {
        String messageCancelMatch = getResources().getString(R.string.dialog_cancel_match);
        String btnYesString = getResources().getString(R.string.yes_txt_btn);
        String btnNoString = getResources().getString(R.string.no_txt_btn);
        new AlertDialog.Builder(this)
                .setMessage("Match terminée")
                .setPositiveButton(btnYesString, (dialogInterface, i) -> {

                    finish();
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                })
                .setNegativeButton(btnNoString, null)
                .show();

    }

    private void showDialogClubs(Club club1, Club club2) {
            play1String = club1.getName();
            play2String = club2.getName();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.dialog_serve_player));
            final String [] langs = {play1String, play2String};
            builder.setSingleChoiceItems(langs, -1, (dialogInterface, i) -> {
                if(i == 0) {

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
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


    @Override
    public void onDataReceived(List<Upload> uploadList) {

    }
}