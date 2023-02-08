package com.example.wagbaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Restaurant extends AppCompatActivity implements IDishesLoadListener, ICartLoadListener {
//    ArrayList<DishesModels> DishModels = new ArrayList<>();
//    int[] DishImages= {R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4};

    DishRecyclerViewAdapter adapter2;
    //Button AddtoCart; ;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.btnCart)
    FrameLayout btnCart;
    @BindView(R.id.recyclerView2)
    RecyclerView rv2;
    IDishesLoadListener dishesLoadListener;
    ICartLoadListener cartLoadListener;
//    RecyclerView rv2 = findViewById(R.id.recyclerView2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
//        RecyclerView rv2 = findViewById(R.id.recyclerView2);
            //rv2.setHasFixedSize(true);
      //  rv2.setLayoutManager(new LinearLayoutManager(this));

//        String key = getIntent().getStringExtra("RestID");
//        FirebaseRecyclerOptions<DishesModels> options = new FirebaseRecyclerOptions.Builder<DishesModels>()
//                .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurants").child(key).child("Dishes"), DishesModels.class)
//                .build();


        //setupDishesModels();
        //set adapter created to the rv after setting models to not pass empty array list



        //add to cart

        init();
        loadDishesFromFirebase();
        countCartItem();

    }

    private void loadDishesFromFirebase() {
        String key = getIntent().getStringExtra("RestID");
        List<DishesModels> dishesModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference().child("Restaurants").child(key).child("Dishes")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                           for(DataSnapshot dishSnapshot:snapshot.getChildren()) {
                               DishesModels dishModel = dishSnapshot.getValue(DishesModels.class);
                               dishModel.setKey(dishSnapshot.getKey());
                               dishesModels.add(dishModel);
                           }
                           dishesLoadListener.onDishLoadSuccess(dishesModels);
                        }
                        else
                            dishesLoadListener.onDishLoadFailed("Cannot find data");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        dishesLoadListener.onDishLoadFailed(error.getMessage());
                    }
                });
    }

    private void init(){
        ButterKnife.bind(this);
        dishesLoadListener=this;
        cartLoadListener=this;

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        rv2.setLayoutManager(gridLayoutManager);
        rv2.addItemDecoration(new SpaceItemDecoration());

        btnCart.setOnClickListener(v -> {
            startActivity(new Intent(this, Cart.class));
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter2.startListening();
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        adapter2.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter2.stopListening();
//    }

    @Override
    public void onDishLoadSuccess(List<DishesModels> dishmodelList) {
        adapter2 = new DishRecyclerViewAdapter(this,dishmodelList, cartLoadListener);
        rv2.setAdapter(adapter2);
    }

    @Override
    public void onDishLoadFailed(String message) {
        Toast.makeText(Restaurant.this, "cannot load data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnCartLoadSuccess(List<CartModel> cartmodelList) {
        int cartSum =0;
        for(CartModel cartModel:cartmodelList){
            cartSum += cartModel.getQuantity();


        }
        badge.setNumber(cartSum);
    }

    @Override
    public void onCartLoadFailed(String message) {
        Toast.makeText(Restaurant.this, "cannot load data", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countCartItem();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        if(EventBus.getDefault().hasSubscriberForEvent(MyUpdateCartEvent.class))
        {
            EventBus.getDefault().removeStickyEvent(MyUpdateCartEvent.class);
        }
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onUpdateCart(MyUpdateCartEvent event)
    {
        countCartItem();
    }

    private void countCartItem() {
        List<CartModel> cartModels=new ArrayList<>();
        FirebaseDatabase
                .getInstance().getReference("Cart")
                .child("UNIQUE_USER-ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot cartSnapshot: snapshot.getChildren()){
                            CartModel cartModel = cartSnapshot.getValue(CartModel.class);
                            cartModel.setKey(cartSnapshot.getKey());
                            cartModels.add(cartModel);

                        }
                        cartLoadListener.OnCartLoadSuccess(cartModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartLoadListener.onCartLoadFailed(error.getMessage());
                    }
                });
    }
}
//    private void setupDishesModels(){
//        //create all model classes in each of 4 Dishes
//        //get names from string array created in the strings xml file
//        String[] DishesNames= getResources().getStringArray(R.array.Dishes);
//        String[] DishesPrices=getResources().getStringArray(R.array.Prices);
//        Button addcart=findViewById(R.id.btnadd);
//        //loop through this names then, create models
//        for ( int i=0; i<DishesNames.length;i++){
//            //to store the created models in the list of rest models created above
//            DishModels.add(new DishesModels(DishesNames[i],
//                    DishesPrices[i],addcart,
//                    DishImages[i]));
//        }
//    }
