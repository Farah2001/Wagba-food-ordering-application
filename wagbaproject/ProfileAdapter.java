package com.example.wagbaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    Context context;

    public ProfileAdapter(Context context, List<UserModel> list) {
        this.context = context;
        this.list = list;
    }

    List<UserModel> list;

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView NAME,USRNAME,PNUM;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            NAME=itemView.findViewById(R.id.NAME);
            USRNAME=itemView.findViewById(R.id.USRNAME);
            PNUM=itemView.findViewById(R.id.PHONENUM);
        }
    }
    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row4,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        holder.PNUM.setText(list.get(position).getPnum());
        holder.USRNAME.setText(list.get(position).getUsrname());
        holder.NAME.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
