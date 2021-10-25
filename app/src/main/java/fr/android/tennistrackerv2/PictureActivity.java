package fr.android.tennistrackerv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import fr.android.tennistrackerv2.Adapter.ImageUploadAdapter;
import fr.android.tennistrackerv2.Model.Upload;
import fr.android.tennistrackerv2.ViewModel.UploadViewModel;

public class PictureActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageUploadAdapter imageUploadAdapter;
    private UploadViewModel uploadViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("Pictures");


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setSelectedItemId(R.id.picturePage);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.matchBoard:
                    startActivity(new Intent(getApplicationContext(), MatchBottomBarActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.localisationPage:
                    startActivity(new Intent(getApplicationContext(), LocationActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.picturePage:
                    return true;
            }
            return false;
        });

        recyclerView = findViewById(R.id.recycler_view_img);

        uploadViewModel = new ViewModelProvider(this).get(UploadViewModel.class);
        uploadViewModel.getUploadList().observe(this, uploadsFirebase -> {
            //uploads = uploadsFirebase;
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            imageUploadAdapter = new ImageUploadAdapter(this, uploadsFirebase);
            recyclerView.setAdapter(imageUploadAdapter);
            imageUploadAdapter.notifyDataSetChanged();

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
            if(ContextCompat.checkSelfPermission(PictureActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ;
                ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.CAMERA
                }, 100);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);


            return true;
        }
        /**
         else if(item.getItemId() == R.id.action_done) {

         matchRef = FirebaseDatabase.getInstance().getReference().child("Match");
         MatchFragment matchFragment = (MatchFragment) fragmentAdapter.createFragment(0);
         System.out.println(matchFragment.getClub1());
         //Match match = new Match(club1, club2, strDate, "8 Impasse Eugene Ducretet");
         //matchRef.push().setValue(match);
         Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show();
         showDialogDoneMatch();
         }
         */
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
                .setPositiveButton(btnYesString, (dialogInterface, i) -> {
                    finish();
                    Intent intent = new Intent(this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                })
                .setNegativeButton(btnNoString, null)
                .show();

    }

    @Override
    public void onBackPressed() {
        showDialogCancelMatch();
    }

}