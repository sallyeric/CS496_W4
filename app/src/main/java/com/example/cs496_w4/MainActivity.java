package com.example.cs496_w4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static String user_ID;
    public static String user_profile="";
    private final String TAG = "MAIN";

    private FirebaseFirestore mFireStoreRef = FirebaseFirestore.getInstance();  // PHOTOS reference
    private FirebaseFirestore mFireStoreRef2 = FirebaseFirestore.getInstance(); // FRIENDS reference
    private FirebaseFirestore mFireStoreRef3 = FirebaseFirestore.getInstance(); // PLACE reference

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tv = findViewById(R.id.MainText);
        final Button btn = findViewById(R.id.FirebaseBtn);

        // get current user info
        final FirebaseUser user = fAuth.getCurrentUser();
        tv.setText(user.getEmail());

        // PHOTOS DB input
        final ArrayList<String> friends = new ArrayList<>();
        friends.add("JEK");
        friends.add("ABS");

        SimpleDateFormat s = new SimpleDateFormat("yyyy-mm-dd");
        String timeStamp = s.format(new Date());

        final boolean isLiked = false;

        final GeoPoint location = new GeoPoint(37, 127);

        final Photo photo = new Photo("test url", friends, timeStamp, location, isLiked);
        final HashMap<String, Object> data = photo.toMap();

        // FRIENDS DB input
        final ArrayList<String> imgUrls = new ArrayList<>();
        imgUrls.add("https://aaa.io");
        imgUrls.add("https://bbb.io");
        imgUrls.add("https://ccc.io");
        final Friends friend = new Friends("Sally", imgUrls);
        final HashMap<String, Object> data2 = friend.toMap();

        // PLACE DB input
        final GeoPoint location2 = new GeoPoint(37, 127);
        final ArrayList<String> imgUrls2 = new ArrayList<>();
        imgUrls2.add("https://aaa.io");
        imgUrls2.add("https://bbb.io");
        imgUrls2.add("https://ccc.io");
        final Place place = new Place(location2,"Pizza Hut", imgUrls2);
        final HashMap<String, Object> data3 = place.toMap();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = user.getEmail();
                mFireStoreRef
                        .collection("Users")
                        .document(userEmail)
                        .update("Photos", data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                            }
                        });

                mFireStoreRef2
                        .collection("Users")
                        .document(userEmail)
                        .update("Friends", data2)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                            }
                        });

                mFireStoreRef3
                        .collection("Users")
                        .document(userEmail)
                        .update("Place", data3)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                            }
                        });


//                String userEmail = user.getEmail();
//
//                WriteBatch batch = mFireStoreRef.batch();
//
//                // Set the value of 'NYC'
//                DocumentReference nycRef = mFireStoreRef.collection("Places").document(userEmail);
//                batch.update(nycRef, "color", FieldValue.arrayUnion("yellow"));
//
//                // Commit the batch
//                batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                        try {
//                            task.getException();
//                        }
//                        catch (Exception e)
//                        {
//                            e.printStackTrace();
//                        }
//                        Log.d("FIREBASE", "upload end");
//                    }
//                });
            }
        });
    }

    /*TODO
    * 로그아웃 어디에 넣어줄까 ?? */
    public void logout (View view){
        FirebaseAuth.getInstance().signOut(); // 로그아웃 전까지 유저의 로그인 상태를 유지 해주는 듯 ??
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}