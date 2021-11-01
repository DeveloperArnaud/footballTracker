package fr.android.tennistrackerv2;

import android.os.AsyncTask;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class ParserTask extends AsyncTask<String, Integer, List<HashMap<String, String>>> {
    GoogleMap map;
    public ParserTask(GoogleMap map) {
        this.map = map;
    }
    @Override
    protected List<HashMap<String, String>> doInBackground(String... strings) {
        JsonParser jsonParser = new JsonParser();
        List<HashMap<String, String>> mapList = null;
        JSONObject object = null;
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
        hashMaps.clear();

        for(int i = 0; i < hashMaps.size(); i++) {
            HashMap<String, String> hashMap = hashMaps.get(i);
            double lat = Double.parseDouble(hashMap.get("lat"));
            double lng = Double.parseDouble(hashMap.get("lng"));
            String name = hashMap.get("name");
            LatLng latLng = new LatLng(lat, lng);
            MarkerOptions options = new MarkerOptions();
            options.position(latLng);
            options.title(name);
        }
    }
}
