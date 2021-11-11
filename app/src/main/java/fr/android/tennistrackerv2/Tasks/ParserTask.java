package fr.android.tennistrackerv2.Tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import fr.android.tennistrackerv2.CreateMatchActivity;
import fr.android.tennistrackerv2.Parser.JsonParser;
import fr.android.tennistrackerv2.R;

public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> implements GoogleMap.OnMarkerClickListener {
    GoogleMap map;
    LatLng myLatLng;
    Marker stadiumMarkers;
    Context context;
    double lat, lng = 0;
    String photo_ref;

    public ParserTask(GoogleMap map, LatLng myLatLng, Context context) {
        this.map = map;
        this.myLatLng = myLatLng;
        this.context = context;

    }
    @Override
    protected List<HashMap<String, String>> doInBackground(String... strings) {
        JsonParser jsonParser = new JsonParser();
        List<HashMap<String, String>> mapList = null;
        JSONObject object;
        try {
            object = new JSONObject(strings[0]);
            mapList = jsonParser.parseResult(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mapList;
    }

    @Override
    protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
        map.clear();
        for(int i = 0; i < hashMaps.size(); i++) {
            HashMap<String, String> hashMap = hashMaps.get(i);
            lat = Double.parseDouble(hashMap.get("lat"));
            lng = Double.parseDouble(hashMap.get("lng"));
            String vicinity = hashMap.get("vicinity");
            String name = hashMap.get("name");
            photo_ref = hashMap.get("photo_ref");
            LatLng latLng = new LatLng(lat, lng);
            MarkerOptions me = new MarkerOptions();

            stadiumMarkers = map.addMarker(new MarkerOptions()
            .title(name)
                    .snippet(vicinity)
            .position(latLng));
            me.title(context.getResources().getString(R.string.marker_me));
            me.position(myLatLng);
            me.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            map.addMarker(me);
            map.setOnMarkerClickListener(this);
        }
    }


    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        final Handler handler  = new Handler();

        String infoAddress = marker.getTitle()+", "+marker.getSnippet();
        if (!marker.getTitle().equals(context.getResources().getString(R.string.marker_me))) {
            final Runnable runnable = () -> showDialog(infoAddress, marker, photo_ref);
            handler.postDelayed(runnable, 900);
        } else {
            return false;
        }
        return false;
    }

    public void showDialog(String message, Marker marker, String photo_ref) {
        new AlertDialog.Builder(context)
                .setMessage(context.getResources().getString(R.string.pick_stadium))
                .setPositiveButton(context.getResources().getString(R.string.yes_txt_btn), (dialogInterface, i) -> {
                    Intent intent = new Intent(context, CreateMatchActivity.class);
                    intent.putExtra("address", message);
                    intent.putExtra("title", marker.getTitle());
                    intent.putExtra("snippet", marker.getSnippet());
                    intent.putExtra("lat", marker.getPosition().latitude);
                    intent.putExtra("lng", marker.getPosition().longitude);
                    intent.putExtra("photo_ref", photo_ref);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                })
                .setNegativeButton(context.getResources().getString(R.string.no_txt_btn), null)
                .show();

    }
}
