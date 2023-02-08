package com.example.wagbaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class orderHistoryAdapter extends RecyclerView.Adapter<orderHistoryAdapter .MyViewHolder>{

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<OrderHistoryModel> ordermodels;

    public orderHistoryAdapter(Context context, ArrayList<OrderHistoryModel> ordermodels, RecyclerViewInterface recyclerViewInterface) {
        this.context=context;
        this.ordermodels=ordermodels;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    @NonNull
    @Override
    public orderHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.recycler_view_row5,parent,false);
        return new orderHistoryAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull orderHistoryAdapter.MyViewHolder holder, int position) {
        holder.ordername.setText(ordermodels.get(position).getStatus());
        holder.orderprice.setText(ordermodels.get(position).getGate() + " " + ordermodels.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return ordermodels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView ordername, orderprice,orderdate,ordertime;


        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface ) {
            super(itemView);

            ordername = itemView.findViewById(R.id.textdishname);
            orderprice = itemView.findViewById(R.id.textprice);
            img = itemView.findViewById(R.id.dishimg1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!= null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onitemclick(position);
                        }
                    }
                }
            });

        }
    }
}

