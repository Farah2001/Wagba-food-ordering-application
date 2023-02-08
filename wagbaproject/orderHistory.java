package com.example.wagbaproject;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;

public class orderHistory extends AppCompatActivity implements RecyclerViewInterface {

    ArrayList<OrderHistoryModel> ordermodels = new ArrayList<>();
    RecyclerView RVhist;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        RVhist = findViewById(R.id.rvhist);
        setupOrderModel();

        //btnBack.setOnClickListener(v -> finish());
    }

    private void setupOrderModel() {
        FirebaseDatabase.getInstance().getReference().child("usersOrders").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot restaurants : snapshot.getChildren()) {
                    final String clientUid = restaurants.child("clientUid").getValue(String.class);
                    final String date = restaurants.child("date").getValue(String.class);
                    final String gate = restaurants.child("gate").getValue(String.class);
                    final String status = restaurants.child("status").getValue(String.class);
                    final String time = restaurants.child("time").getValue(String.class);
                    final ArrayList<CartModel> items = new ArrayList<>();
                    for (DataSnapshot item : restaurants.child("items").getChildren()) {
                        items.add(item.getValue(CartModel.class));
                    }
                    OrderHistoryModel resmodels = new OrderHistoryModel(status, date, clientUid, gate, time, items);
                    ordermodels.add(resmodels);
                }
                orderHistoryAdapter adapterhist = new orderHistoryAdapter(orderHistory.this, ordermodels, orderHistory.this);

                RVhist.setAdapter(adapterhist);
                RVhist.setLayoutManager(new LinearLayoutManager(orderHistory.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public void onitemclick(int position) {

    }
}

