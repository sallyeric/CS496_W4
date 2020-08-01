package com.example.cs496_w4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static String user_ID;
    public static String user_profile="";

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*TODO
    * 로그아웃 어디에 넣어줄까 ?? */
    public void logout (View view){
        FirebaseAuth.getInstance().signOut(); // 로그아웃 전까지 유저의 로그인 상태를 유지 해주는 듯 ??
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}