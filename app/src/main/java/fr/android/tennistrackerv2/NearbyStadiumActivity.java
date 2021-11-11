package fr.android.tennistrackerv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

import fr.android.tennistrackerv2.Tasks.PlaceTask;


public class NearbyStadiumActivity extends AppCompatActivity {

    Button btnFindStadium;
    SupportMapFragment supportMapFragment;
    GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient;
    double currentLat = 0;
    double currentLong = 0;
    LatLng latLng;
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_stadium);
        btnFindStadium = findViewById(R.id.btnFindStadiums);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_mapK);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if(ActivityCompat.checkSelfPermission(NearbyStadiumActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(NearbyStadiumActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        //Get nearby places with API call
        btnFindStadium.setOnClickListener(view -> {
            String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+currentLat +","+ currentLong+"&radius=5000&types=stadium&sensor=true&key="+getResources().getString(R.string.google_map_key);
            System.out.println(url);
            latLng = new LatLng(currentLat, currentLong);
            new PlaceTask(googleMap, latLng, NearbyStadiumActivity.this).execute(url);
        });
    }

    private void getCurrentLocation() {
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if(location != null) {
                currentLat = location.getLatitude();
                currentLong = location.getLongitude();

                supportMapFragment.getMapAsync(map -> {
                    googleMap = map;
                    System.out.println(googleMap);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                    markerOptions.title(getResources().getString(R.string.marker_me));
                    try {
                        geocoder = new Geocoder(this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(currentLat, currentLong, 1);
                        String add = addresses.get(0).getAddressLine(0).split(",")[0];
                        markerOptions.snippet(add);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    latLng = new LatLng(currentLat, currentLong);
                    markerOptions.position(latLng);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat, currentLong), 11));
                    googleMap.addMarker(markerOptions);
                });
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }
}

