package com.barcodescanner.models.qrcodes;

import com.barcodescanner.interfaces.ModelOperations;

public class Location implements ModelOperations {

    private String latitude;
    private String longitude;
    private String altitude;

    public Location(String latitude, String longitude, String altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    @Override
    public String toSchema() {
        return "geo:" + latitude + "," + longitude + (!altitude.equals("") ? "," + altitude : "");
    }
}
