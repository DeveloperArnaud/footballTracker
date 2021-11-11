package fr.android.tennistrackerv2.Model;

import java.io.Serializable;

public class Address implements Serializable {

    private String nameStadium;
    private String streetName;
    private double lat;
    private double lng;

    public Address() {
    }

    public Address(String nameStadium, String streetName, double lat, double lng) {
        this.nameStadium = nameStadium;
        this.streetName = streetName;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNameStadium() {
        return nameStadium;
    }

    public String getStreetName() {
        return streetName;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
