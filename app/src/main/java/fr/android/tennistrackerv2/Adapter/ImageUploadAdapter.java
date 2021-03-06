package fr.android.tennistrackerv2.Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

import fr.android.tennistrackerv2.Model.Upload;
import fr.android.tennistrackerv2.NearbyStadiumActivity;
import fr.android.tennistrackerv2.R;

public class ImageUploadAdapter extends RecyclerView.Adapter<ImageUploadAdapter.ImageViewHolder> {
    private Context context;
    private List<Upload> uploads;
    private String txtDownloadImg;
    private String txtYes;
    private String txtNo;
    private String txtImageSaved;

    public ImageUploadAdapter(Context context, List<Upload> uploads) {
        this.context = context;
        this.uploads = uploads;
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public ImageButton imageButton;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_upload_view);
            imageButton = itemView.findViewById(R.id.btnDownload);
            txtDownloadImg = context.getResources().getString(R.string.txtDownloadImg);
            txtYes = context.getResources().getString(R.string.yes_txt_btn);
            txtNo = context.getResources().getString(R.string.no_txt_btn);
            txtImageSaved = context.getResources().getString(R.string.txtImageSaved);

        }
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload upload = uploads.get(position);
        Picasso.with(context)
                .load(upload.getImgUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);
        holder.imageButton.setOnClickListener(view -> new AlertDialog.Builder(context)
                .setMessage(txtDownloadImg)
                .setPositiveButton(txtYes, (dialogInterface, i) -> downloadImg(holder.imageView, context))
                .setNegativeButton(txtNo, null)
                .show());

    }

    private void downloadImg(ImageView imageView, Context context) {


        if(imageView != null) {
            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Image_"+bitmap.getGenerationId(),"" + + bitmap.getGenerationId());
            Toast.makeText(context,txtImageSaved , Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }


}
