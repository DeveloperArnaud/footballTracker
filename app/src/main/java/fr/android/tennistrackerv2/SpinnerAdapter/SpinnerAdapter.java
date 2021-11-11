package fr.android.tennistrackerv2.SpinnerAdapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import fr.android.tennistrackerv2.Model.Club;
import fr.android.tennistrackerv2.R;

public class SpinnerAdapter extends ArrayAdapter<Club> {

    LayoutInflater layoutInflater;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Club> clubList) {
        super(context, resource, clubList);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.custom_spinner_club_adapter, null , true);
        Club club = getItem(position);
        TextView textView = view.findViewById(R.id.txtView_spinner_club);
        ImageView imgView = view.findViewById(R.id.imgView_spinner);
        textView.setText(club.getName());
        Picasso.with(view.getContext())
                .load(club.getImgUrl())
                .fit()
                .centerCrop()
                .into(imgView);

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.custom_spinner_club_adapter, parent, false);
        Club club = getItem(position);
        TextView textView = convertView.findViewById(R.id.txtView_spinner_club);
        ImageView imgView = convertView.findViewById(R.id.imgView_spinner);
        textView.setText(club.getName());
        Picasso.with(convertView.getContext())
                .load(club.getImgUrl())
                .into(imgView);
        return convertView;
    }

}
