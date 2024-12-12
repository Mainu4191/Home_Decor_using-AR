package com.example.arapp3.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arapp3.Constant;
import com.example.arapp3.Model.OrderDetails;
import com.example.arapp3.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder>{

    List<OrderDetails> orderDetailsList;
    Context context;

    public OrderDetailsAdapter(List<OrderDetails> orderDetailsList, Context context){
        this.orderDetailsList = orderDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details_row, parent, false);
        return new ViewHolder(view, context);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetails orderDetails = orderDetailsList.get(position);
        holder.Name.setText(orderDetails.getName());
        holder.Price.setText("Price: " + String.valueOf(orderDetails.getPrice()));
        holder.Quantity.setText("Quantity: " + String.valueOf(orderDetails.getQuantity()));
        double total = orderDetails.getPrice() * orderDetails.getQuantity();
        holder.Total.setText("Total: " + String.valueOf(total));

        String imageUrl = Constant.IMAGE_URL + orderDetails.getFolderName() + "/" + orderDetails.getImage();
        Picasso.with( context )
                .load( imageUrl )
                .placeholder( R.drawable.ic_launcher_background )
                .fit()
                .centerInside()
                .into( holder.Image );
    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView Image;
        TextView Name, Price, Quantity, Total;
        public ViewHolder(View itemView, Context ctx){
            super(itemView);
            context = ctx;

            Image = itemView.findViewById(R.id.iv_order_details_row);
            Name = itemView.findViewById(R.id.tv_order_details_row_name);
            Price = itemView.findViewById(R.id.tv_order_details_row_price);
            Quantity = itemView.findViewById(R.id.tv_order_details_row_quantity);
            Total = itemView.findViewById(R.id.tv_order_details_row_total);

        }
    }
}
