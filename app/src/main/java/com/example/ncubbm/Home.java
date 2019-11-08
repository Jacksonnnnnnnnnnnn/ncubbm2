package com.example.ncubbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void toActivity1(View v) {
        Intent i = new Intent (this, MainActivity.class);
        startActivity(i);
    }

    public void toActivity2(View v) {
        Intent i = new Intent (this, main2.class);
        startActivity(i);
    }
}
