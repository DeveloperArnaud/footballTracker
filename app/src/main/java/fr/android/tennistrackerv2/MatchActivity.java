package fr.android.tennistrackerv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class MatchActivity extends AppCompatActivity {


        ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        showDialogPlayers();
        imgView = findViewById(R.id.imgView);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Match");
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
            if(ContextCompat.checkSelfPermission(MatchActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
;
                ActivityCompat.requestPermissions(this, new String[] {
                        Manifest.permission.CAMERA
                }, 100);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 100);


            return true;
        }// If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 100 && data != null) {
                Bundle bundle = data.getExtras();
                Bitmap photo = (Bitmap) bundle.get("data");
                imgView.setImageBitmap(photo);
            }

    }

    private void showDialogPlayers() {

        String play1String = getResources().getString(R.string.player_1_editTxt);
        String play2String = getResources().getString(R.string.player_2_editTxt);
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

    @Override
    public void onBackPressed() {
        showDialogCancelMatch();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}