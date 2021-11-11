package fr.android.tennistrackerv2.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

import fr.android.tennistrackerv2.Adapter.ImageUploadAdapter;
import fr.android.tennistrackerv2.R;
import fr.android.tennistrackerv2.UploadActivity;
import fr.android.tennistrackerv2.ViewModel.UploadViewModel;

public class PictureFragment extends Fragment {

    FloatingActionButton fab_pictures;
    private RecyclerView recyclerView;
    private ImageUploadAdapter imageUploadAdapter;
    private UploadViewModel uploadViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_pictures_test, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_img);
        uploadViewModel = new ViewModelProvider(this).get(UploadViewModel.class);
        uploadViewModel.getUploadList().observe(getActivity(), uploads -> {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            imageUploadAdapter = new ImageUploadAdapter(getActivity(), uploads);
            recyclerView.setAdapter(imageUploadAdapter);
        });

       fab_pictures = view.findViewById(R.id.fab_btn_picture);
       fab_pictures.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                openCameraPermission();
           }
       });
       return view;
    }

    private void openCameraPermission() {
        if(ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Si permission non accordée, on fait une reqûete de permission
            requestPermissions(new String[] {
                    Manifest.permission.CAMERA
            }, 100);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 100);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(getActivity(), UploadActivity.class);
        // Le code envoyée par la permission est 100, alors la permission a été accordée.
        if(requestCode == 100 && data != null) {
            Bundle bundle = data.getExtras();
            if(bundle != null) {
                Bitmap photo = (Bitmap) bundle.get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
            } else {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
        }


    }
}
