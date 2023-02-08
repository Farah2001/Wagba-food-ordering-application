package com.example.wagbaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ProfileData extends AppCompatActivity {

    RecyclerView rv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);
        rv4=findViewById(R.id.recyclerview4);
        
        getData();
    }

    private void getData() {
        rv4.setAdapter(new ProfileAdapter(getApplicationContext(),DatabaseClass.getDatabase(getApplicationContext()).getDao().getAllData()));
    }
}