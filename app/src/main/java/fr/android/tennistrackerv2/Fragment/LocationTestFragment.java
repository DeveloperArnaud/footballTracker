package fr.android.tennistrackerv2.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import fr.android.tennistrackerv2.NearbyStadiumActivity;
import fr.android.tennistrackerv2.R;
import fr.android.tennistrackerv2.Tasks.PlaceTask;

public class LocationTestFragment extends Fragment {

    LatLng latLng;
    GoogleMap googleMap;
    double currentLat, currentLong = 0;
    Bundle bundle;
    String title;
    String snippet;
    TextView title_stadium;
    TextView snippet_stadium;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_test, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map_location);

        bundle = getArguments();
        title_stadium = view.findViewById(R.id.title_stadium);
        snippet_stadium = view.findViewById(R.id.snippet_stadium);
        currentLat = bundle.getDouble("lat");
        currentLong = bundle.getDouble("lng");
        title = bundle.getString("title");
        snippet = bundle.getString("snippet");

        title_stadium.setText(title);
        snippet_stadium.setText(snippet);
        supportMapFragment.getMapAsync(map -> {
            googleMap = map;
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(title);
            markerOptions.snippet(snippet);
            latLng = new LatLng(currentLat, currentLong);
            markerOptions.position(latLng);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat, currentLong), 15));
            googleMap.addMarker(markerOptions);
        });
        return view;

    }

}
