package com.example.cs496_w4;

import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;

public class Friends {

    private String name;
    private ArrayList<String> imgUrls;

    public Friends() {

    }

    public Friends(String name,ArrayList<String> imgUrls)
    {
        this.name = name;
        this.imgUrls = imgUrls;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setImgUrls(ArrayList<String> imgUrls)
    {
        this.imgUrls = imgUrls;
    }

    public HashMap<String, Object> toMap(){
        HashMap<String, Object> result=new HashMap<>();
        result.put("name", name);
        result.put("imgUrls", imgUrls);

        return result;
    }
}
