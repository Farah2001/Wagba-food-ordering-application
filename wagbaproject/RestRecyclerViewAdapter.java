package com.example.wagbaproject;

//import static com.example.wagbaproject.Restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RestRecyclerViewAdapter extends FirebaseRecyclerAdapter<RestaurantsModels,RestRecyclerViewAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public RestRecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<RestaurantsModels> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull RestaurantsModels model) {
        holder.restname.setText(model.getRestName());
        Glide.with(holder.restimg.getContext())
                .load(model.getRestImg())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.restimg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), Restaurant.class);
                intent.putExtra("RestID", RestRecyclerViewAdapter.this.getRef(holder.getBindingAdapterPosition()).getKey());

                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        ImageView restimg;
        TextView restname;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            restimg = (ImageView)itemView.findViewById(R.id.dishimg1);
            restname=(TextView) itemView.findViewById(R.id.textView);

        }
    }
}
//    //constructor
//    Context context;
//    ArrayList<RestaurantsModels> RestaurantsModels;
//
//    public RestRecyclerViewAdapter(Context context, ArrayList<RestaurantsModels> RestaurantsModels ){
//        this.context=context;
//        this.RestaurantsModels=RestaurantsModels;
//    }
//    @NonNull
//    @Override
//    public RestRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        //give look to rows, inflate layout
//        //giving layput to each row
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view=inflater.inflate(R.layout.recycler_view_row,parent,false);
//        return new RestRecyclerViewAdapter.MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RestRecyclerViewAdapter.MyViewHolder holder, int position) {
//        //assigning values to rows of recycler view when new views are loaded or views come back on the screen or entered the screen
//        //acc to position of the recycler view
//
//        //tell adapter to update data on each of rows
//        //we need to change values within the holder that is passed within
//        holder.restname.setText(RestaurantsModels.get(position).getRestName());
//        //holder.img.setImageResource(Integer.parseInt(RestaurantsModels.get(position).getImage()));
//    }
//
//    @Override
//    public int getItemCount() {
//        //how many items in total
//        return RestaurantsModels.size(); //10
//    }
//
//    //
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        //grab views from recycler view row layout, similar to onCreate
//        ImageView img;
//        TextView restname;
//
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            img = itemView.findViewById(R.id.dishimg1);
//            restname=itemView.findViewById(R.id.textView);
//        }
//    }
//}
