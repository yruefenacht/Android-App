package com.example.bruefy.pointsofinterest;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by bruefy on 29.06.2016.
 */
public class Orte {

    public String title;
    String getTitle(){ return title;}
    void setTitle(String title){this.title = title;}

    public LatLng coordinates;
    LatLng getCoordinates(){return coordinates;}
    void setCoordinates(LatLng coordinates){this.coordinates = coordinates;}

}
