package com.example.cs496_w4;

import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;

public class Place {
    private GeoPoint location;
    private String placeName;
    private ArrayList<String> imgUrls;

    public Place() {

    }

    public Place(GeoPoint location, String placeName, ArrayList<String> imgUrls)
    {
        this.location = location;
        this.placeName = placeName;
        this.imgUrls = imgUrls;
    }

    public void setLocation(GeoPoint location)
    {
        this.location = location;
    }

    public void setPlaceName(String placeName)
    {
        this.placeName = placeName;
    }

    public void setImgUrls(ArrayList<String> imgUrls)
    {
        this.imgUrls = imgUrls;
    }

    public HashMap<String, Object> toMap(){
        HashMap<String, Object> result=new HashMap<>();
        result.put("location", location);
        result.put("placeName", placeName);
        result.put("imgUrls", imgUrls);

        return result;
    }

}
