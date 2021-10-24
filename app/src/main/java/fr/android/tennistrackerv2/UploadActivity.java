package fr.android.tennistrackerv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import fr.android.tennistrackerv2.Model.Upload;

public class UploadActivity extends AppCompatActivity {

    private ImageView imageView;
    private static final int IMG_REQUEST = 10;

    private Button btnUpload;
    private ProgressBar progressBar;
    private Uri imageUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private StorageTask task;
    private String txtUploadInProgress;
    private String txtUploadSuccessful;
    private String txtUploadFailed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        imageView = findViewById(R.id.imageView);
        btnUpload = findViewById(R.id.btnUpload);
        progressBar = findViewById(R.id.progress_upload);
        txtUploadInProgress = getResources().getString(R.string.txtUploadInProgress);
        txtUploadSuccessful = getResources().getString(R.string.txtUploadSuccesful);
        txtUploadFailed = getResources().getString(R.string.txtUploadFailed);
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView.setImageBitmap(bmp);
        storageReference = FirebaseStorage.getInstance().getReference("Picture");
        databaseReference = FirebaseDatabase.getInstance().getReference("Picture");
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bmp , "Title", null);
        imageUri = Uri.parse(path);
        btnUpload.setOnClickListener(view -> {
            if(task != null && task.isInProgress()) {
                Toast.makeText(this, txtUploadInProgress, Toast.LENGTH_SHORT).show();
            } else {
                uploadImg();

            }
        });

    }

    private String getFileExtensions(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImg() {
        if(imageUri != null) {
            StorageReference fileRef = storageReference.child(System.currentTimeMillis()+"."+getFileExtensions(imageUri));

            task = fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    Handler handler = new Handler();
                    handler.postDelayed(() -> progressBar.setProgress(0), 500);
                    Upload upload = new Upload("",uri.toString());
                    String uploadId = databaseReference.push().getKey();
                    databaseReference.child(uploadId).setValue(upload);
                    finish();
                });
                Toast.makeText(this, txtUploadSuccessful, Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }).addOnProgressListener(snapshot -> {
                double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                progressBar.setProgress((int) progress);
            });
        } else {
            Toast.makeText(this, "Upload failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDialogCancelUploadImg() {
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
        showDialogCancelUploadImg();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}