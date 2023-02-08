package com.example.wagbaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Home extends AppCompatActivity{
   // ArrayList<RestaurantsModels> RestModels = new ArrayList<>();
    //int[] RestImages= {R.drawable.arbys, R.drawable.bk, R.drawable.dominos, R.drawable.hardees, R.drawable.kfc, R.drawable.mac, R.drawable.pandahouse, R.drawable.starbucks, R.drawable.subway, R.drawable.papajohns};
   RestRecyclerViewAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        RecyclerView rv = findViewById(R.id.recyclerview1);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //setUpRestaurantModels();
        //set adapter created to the rv after setting models to not pass empty array list
       // FirebaseRecyclerOptions<RestaurantsModels> options = null;
        FirebaseRecyclerOptions<RestaurantsModels> options =
                new FirebaseRecyclerOptions.Builder<RestaurantsModels>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurants"), RestaurantsModels.class)
                        .build();
        adapter1 = new RestRecyclerViewAdapter(options);
        rv.setAdapter(adapter1);





    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        adapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
    }



}
//    private void setUpRestaurantModels(){
//      //create all model classes in each of 10 restaurants
//        //get names from string array created in the strings xml file
//        String[] RestaurantsNames= getResources().getStringArray(R.array.restaurants);
//        //loop through this names then, create models
//        for ( int i=0; i<RestaurantsNames.length;i++){
//            //to store the created models in the list of rest models created above
//            RestModels.add(new RestaurantsModels(RestaurantsNames[i],RestImages[i]));
//        }
//    }

