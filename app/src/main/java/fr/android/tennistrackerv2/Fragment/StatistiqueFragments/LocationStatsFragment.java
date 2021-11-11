package fr.android.tennistrackerv2.Fragment.StatistiqueFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.android.tennistrackerv2.Model.Address;
import fr.android.tennistrackerv2.Model.Match;
import fr.android.tennistrackerv2.R;

public class LocationStatsFragment extends Fragment {

    LatLng latLng;
    GoogleMap googleMap;
    double currentLat, currentLong = 0;
    Bundle bundle;
    String title;
    String snippet;
    TextView title_stadium;
    TextView snippet_stadium;
    Match match;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_stats, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map_location);

        bundle = getArguments();
        match = (Match) bundle.getSerializable("match");

        title_stadium = view.findViewById(R.id.title_stadium);
        snippet_stadium = view.findViewById(R.id.snippet_stadium);
        currentLat = match.getAddress().getLat();
        currentLong = match.getAddress().getLng();
        title = match.getAddress().getNameStadium();
        snippet = match.getAddress().getStreetName();

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
