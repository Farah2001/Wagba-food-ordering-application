package com.example.wagbaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DishRecyclerViewAdapter extends RecyclerView.Adapter<DishRecyclerViewAdapter.myViewHolder> {

    private Context context;

    public DishRecyclerViewAdapter(Context context, List<DishesModels> dishesModelsList, ICartLoadListener iCartLoadListener) {
        this.context = context;
        this.dishesModelsList = dishesModelsList;
        this.iCartLoadListener = iCartLoadListener;
    }

    private List<DishesModels> dishesModelsList;
    private ICartLoadListener iCartLoadListener;



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_view_row2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
                Glide.with(context)
                .load(dishesModelsList.get(position).getDishImg())
                .into(holder.DishImg);
                holder.DishPrice.setText(new StringBuilder("$").append(dishesModelsList.get(position).getDishPrice()));
                holder.DishName.setText(new StringBuilder().append(dishesModelsList.get(position).getDishName()));

                holder.setListener((view, adapterPosition) -> {
                    addToCart(dishesModelsList.get(position));
                });
    }

    private void addToCart(DishesModels dishesModels) {

        DatabaseReference userCart = FirebaseDatabase
                .getInstance()
                .getReference("Cart") /////////////////!!!
                .child("UNIQUE_USER-ID"); ///////////////!!!!!!!!!
        userCart.child(dishesModels.getKey())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) //if the user has already items in the cart

                        {
                            //update qty and total price
                            CartModel cartModel=snapshot.getValue(CartModel.class);
                            cartModel.setQuantity(cartModel.getQuantity()+1);
                            Map<String, Object> updateData = new HashMap<>();
                            updateData.put("quantity",cartModel.getQuantity());
                            updateData.put("totalPrice",cartModel.getQuantity()* Float.parseFloat(cartModel.getItemprice()));

                            userCart.child(dishesModels.getKey())
                                    .updateChildren(updateData)
                                    .addOnSuccessListener(unused -> {
                                        iCartLoadListener.onCartLoadFailed("Add to Cart Successfully");
                                    })
                                    .addOnFailureListener(e -> iCartLoadListener.onCartLoadFailed(e.getMessage()));
                        }
                        else //if user has no items in cart and want to add new
                        {
                           // NumberFormat nf = NumberFormat.getInstance();
                                CartModel cartModel= new CartModel();
                                cartModel.setItemname(dishesModels.getDishName());
                                cartModel.setItemimg(dishesModels.getDishImg());
                                cartModel.setKey(dishesModels.getKey());
                                cartModel.setItemprice(dishesModels.getDishPrice());
                                cartModel.setQuantity(1);
                                cartModel.setTotalPrice(Float.parseFloat(dishesModels.getDishPrice()));

                                userCart.child(dishesModels.getKey())
                                        .setValue(cartModel)
                                        .addOnSuccessListener(unused -> {
                                            iCartLoadListener.onCartLoadFailed("Add to Cart Successfully");
                                        })
                                        .addOnFailureListener(e -> iCartLoadListener.onCartLoadFailed(e.getMessage()));
                        }
                        EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        iCartLoadListener.onCartLoadFailed(error.getMessage());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return dishesModelsList.size();
    }


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param
     */
//    public DishRecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<DishesModels> options) {
//        super(options);
//    }

//    @Override
//    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull DishesModels model) {
//        holder.dishname.setText(model.getDishName());
//        holder.dishprice.setText(model.getDishPrice());
//
//        Glide.with(holder.dishimg.getContext())
//                .load(model.getDishImg())
//                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
//                .error(com.google.firebase.database.R.drawable.common_google_signin_btn_icon_dark)
//                .into(holder.dishimg);
//
//    }
//
//    @NonNull
//    @Override
//    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row2,parent,false);
//        return new myViewHolder(view);
//    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.dishimg1)
        ImageView DishImg;
        @BindView(R.id.textdishname)
        TextView DishName;
        @BindView(R.id.textprice)
        TextView DishPrice;

        public void setListener(IRecyclerViewClickListener listener) {
            this.listener = listener;
        }

        IRecyclerViewClickListener listener;

        private Unbinder unbinder;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onRecyclerClick(view, getAbsoluteAdapterPosition());
        }
    }
}
//    //constructor
//    Context context2;
//    ArrayList<DishesModels> DishesModels;
//
//    public DishRecyclerViewAdapter(Context context, ArrayList<DishesModels> DishesModels ){
//        this.context2=context;
//        this.DishesModels=DishesModels;
//    }
//    @NonNull
//    @Override
//    public DishRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        //give look to rows, inflate layout
//        //giving layput to each row
//        LayoutInflater inflater = LayoutInflater.from(context2);
//        View view=inflater.inflate(R.layout.recycler_view_row2,parent,false);
//        return new DishRecyclerViewAdapter.MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        //assigning value to rows as they come back on the screen
//        holder.dishname.setText(DishesModels.get(position).getDishName());
//        holder.dishprice.setText(DishesModels.get(position).getDishPrice());
//        holder.dishimg.setImageResource(DishesModels.get(position).getImageDish());
//        holder.adddish.findViewById(R.id.btnadd);
//    }
//
//
//
//    @Override
//    public int getItemCount() {
//        return DishesModels.size(); //4
//    }
//    //
//    //
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        //grab views from recycler view row layout, similar to onCreate
//        ImageView dishimg;
//        TextView dishname;
//        TextView dishprice;
//        Button adddish;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            dishimg = itemView.findViewById(R.id.dishimg1);
//            dishname=itemView.findViewById(R.id.textdishname);
//            dishprice=itemView.findViewById(R.id.textprice);
//            adddish=itemView.findViewById(R.id.btnadd);
//
//        }
//    }

