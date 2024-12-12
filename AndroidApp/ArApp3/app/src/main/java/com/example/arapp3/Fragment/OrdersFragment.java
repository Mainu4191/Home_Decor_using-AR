package com.example.arapp3.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arapp3.Adapter.OrdersAdapter;
import com.example.arapp3.Constant;
import com.example.arapp3.Model.OrderItems;
import com.example.arapp3.Model.OrderModel;
import com.example.arapp3.R;
import com.example.arapp3.Util.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrdersFragment extends Fragment {

    RecyclerView recyclerView;
    List<OrderModel> orderModelList;
    RecyclerView.Adapter adapter;


    String url = Constant.BASE_URL + "/users/api/order/get-by-user-id";
    RequestQueue requestQueue;
    Session session;

    String TAG = "TAG";




    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersFragment() {
        // Required empty public constructor
    }

    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        recyclerView = view.findViewById(R.id.orders_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        session = new Session(getContext());

        int userId = Integer.parseInt(session.getUserId());

        Log.d(TAG, "onCreateView: " + String.valueOf(userId));
        OrderModel orderModel = new OrderModel();
        orderModel.setUser_id(userId);

        Log.d(TAG, "onCreateView: " + orderModel.getUser_id());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", orderModel.getUser_id());
            jsonObject.put("Content-Type", "application/json");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);

        requestQueue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "onResponse: " + response);
                orderModelList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    Log.d(TAG, "onResponse: " + response.length());
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("order_id");
                        Log.d(TAG, "onResponse: order_id " + id);
                        int user_id = jsonObject.getInt("user_id");
                        String order_date = jsonObject.getString("order_date");
                        Log.d(TAG, "onResponse: order_date " + order_date);
                        String order_status = jsonObject.getString("status");
                        Log.d(TAG, "onResponse: order_status " + order_status);
                        double total_price = jsonObject.getDouble("total");
                        Log.d(TAG, "onResponse: total_price " + total_price);

                        JSONArray jsonArray1 = jsonObject.getJSONArray("orderItems");


                        List<OrderItems> orderItemsList = new ArrayList<>();
                       for (int j = 0; j < jsonArray1.length(); j++) {
                           try{
                               JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                               int item_id = jsonObject1.getInt("item_id");
                                 int quantity = jsonObject1.getInt("quantity");
                                    double price = jsonObject1.getDouble("price");
                                    Log.d(TAG, "onResponse: price " + price + "quantity " + quantity + "item_id " + item_id);
                                    OrderItems orderItems = new OrderItems();
                                    orderItems.setItem_id(item_id);
                                    orderItems.setQuantity(quantity);
                                    orderItems.setPrice(price);
                                    orderItemsList.add(orderItems);
                           }catch (JSONException e) {
                               e.printStackTrace();
                           }
                       }

OrderModel orderModel = new OrderModel();
                        orderModel.setOrder_id(id);
                        orderModel.setUser_id(user_id);
                        orderModel.setOrder_date(order_date);
                        orderModel.setStatus(order_status);
                        orderModel.setTotal(total_price);
                        orderModel.setOrderItems(orderItemsList);

                        orderModelList.add(orderModel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                adapter = new OrdersAdapter(orderModelList, getContext());
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        requestQueue.add(jsonArrayRequest);

        return view;
    }
}