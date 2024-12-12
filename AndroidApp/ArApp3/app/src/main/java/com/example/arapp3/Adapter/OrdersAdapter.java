package com.example.arapp3.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arapp3.Constant;
import com.example.arapp3.Fragment.OrderDetailsFragment;
import com.example.arapp3.MainActivity;
import com.example.arapp3.Model.OrderModel;
import com.example.arapp3.R;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    List<OrderModel> orderModelList;
    Context context;

    public OrdersAdapter(List<OrderModel> orderModelList, Context context){
        this.orderModelList = orderModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_row, parent, false);
        return new ViewHolder(view, context);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderModel orderModel = orderModelList.get(position);
        holder.orderId.setText("Order ID: " + orderModel.getOrder_id());

        Timestamp timestamp = Timestamp.valueOf(orderModel.getOrder_date());
        Date date = new Date(timestamp.getTime());
        String formattedDate = DateFormat.getDateInstance().format(date);
        holder.orderDate.setText("Order Date: " + formattedDate);

        holder.orderStatus.setText("Order Status: " + orderModel.getStatus());
        holder.orderTotal.setText("Order Total: " + orderModel.getTotal());

        holder.btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "View Order", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putLong("order_id", orderModel.getOrder_id());
                bundle.putDouble("order_total", orderModel.getTotal());
                bundle.putInt("total_items", orderModel.getOrderItems().size());



                Fragment orderDetailsFragment = new OrderDetailsFragment();
                Context context = v.getContext();
                orderDetailsFragment.setArguments(bundle);

                ((MainActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_container, orderDetailsFragment).addToBackStack(null).commit();



            }
        });

        holder.btnRemoveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long orderId = orderModel.getOrder_id();
                String url = Constant.BASE_URL + "/users/api/order/delete";

                RequestQueue requestQueue = Volley.newRequestQueue(context);

                JSONObject getData = new JSONObject();
                try {
                    getData.put("order_id", orderId);
                    getData.put("Content-Type", "application/json");
                }
                catch(Exception e){
                    Log.d("Error",(e.toString()));
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, url,getData,

                        Response ->{
                            try {
                                Log.d("order", Response.toString());
                                String status = Response.getString("status");

                                if (status.equals("true")){
                                    orderModelList.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, orderModelList.size());
                                    notifyDataSetChanged();
                                    Toast.makeText(context,"Order Deleted...", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Not Deleted...",Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e){
                                Log.d("order", e.toString());
                            }

                },
                        Error ->{
                            Log.d("order", "Error");
                        }
                );
                requestQueue.add(jsonObjectRequest);
            }
        });


    }

    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView orderId, orderDate, orderStatus , orderTotal;
        Button btnViewOrder, btnRemoveOrder;
        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            orderId = itemView.findViewById(R.id.order_id);
            orderDate = itemView.findViewById(R.id.order_date);
            orderStatus = itemView.findViewById(R.id.order_status);
            orderTotal = itemView.findViewById(R.id.order_total);
            btnViewOrder = itemView.findViewById(R.id.order_details);
            btnRemoveOrder = itemView.findViewById(R.id.order_Cancel);
        }
    }






}
