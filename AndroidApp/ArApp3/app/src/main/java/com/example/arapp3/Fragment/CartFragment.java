package com.example.arapp3.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.arapp3.Activity.paymentActivity;
import com.example.arapp3.Adapter.CartAdapter;
import com.example.arapp3.Constant;
import com.example.arapp3.MainActivity;
import com.example.arapp3.Model.CartModel;
import com.example.arapp3.Model.CartModelServer;
import com.example.arapp3.Model.OrderModelServer;
import com.example.arapp3.R;
import com.example.arapp3.Util.Session;
import com.example.arapp3.Util.SqlLiteClass;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    SqlLiteClass sqlLiteClass;
    static List<CartModel> cartModelList;


    RecyclerView recyclerView;
    CartAdapter cartAdapter;


    static TextView totalAmount;
    static  Double totalAmountValue = 0.0;

    Button placeOrder;
    Session session;

    RequestQueue requestQueue;

    Fragment fragment;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        placeOrder = view.findViewById(R.id.checkoutButtonID);
        recyclerView = view.findViewById(R.id.cartRecyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        totalAmount = view.findViewById(R.id.totalPriceID);
        updateTotalAmount(0.00);










        sqlLiteClass = new SqlLiteClass(getContext(), "CartDB");
        Cursor cursor = sqlLiteClass.readData(Constant.CART_TABLE_NAME);




        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
        }else{
            cartModelList = new ArrayList<>();
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                int product_id = cursor.getInt(1);
                String name = cursor.getString(2);
                int price = cursor.getInt(3);
                String description = cursor.getString(4);
                String image = cursor.getString(5);
                String folderName = cursor.getString(6);
                int quantity = cursor.getInt(7);


                cartModelList.add(new CartModel(id, product_id, name, price, description, image, folderName, quantity));

                totalAmountValue = totalAmountValue + (price * quantity);





            }
        }



        cartAdapter = new CartAdapter(cartModelList, getContext());
        recyclerView.setAdapter(cartAdapter);

        updateTotalAmount(totalAmountValue);



        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               

                Session session = new Session(getContext());

                if (!session.getLoggedIn()){
                    Toast.makeText(getContext(), "Please Login", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if(totalAmountValue == 0.0){
                    Toast.makeText(getContext(), "can't place order because cart is empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getContext(), paymentActivity.class) ;
                startActivity(intent);

                int userId = Integer.parseInt(session.getUserId());

                List<CartModelServer> cartModelServerList = new ArrayList<>();

                cartModelList.forEach(cartModel -> {
                   int productId = cartModel.getProduct_id();
                     int quantity = cartModel.getQuantity();
                     int price = cartModel.getPrice();
                    CartModelServer cartModelServer = new CartModelServer(productId, quantity, price);
                    cartModelServerList.add(cartModelServer);

                });


                OrderModelServer orderModelServer = new OrderModelServer(userId, cartModelServerList);

                Gson gson = new Gson();

                String orderModelServerString = gson.toJson(orderModelServer);

                Log.d("CartFragment", "orderModelServerString: " + orderModelServerString);

                requestQueue = Volley.newRequestQueue(getContext());

                String url = Constant.BASE_URL + "/users/api/order/add";


                JSONArray jsonArray = new JSONArray();
                for (CartModel cartModel : cartModelList){
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("item_id", cartModel.getProduct_id());
                        jsonObject.put("quantity", cartModel.getQuantity());
                        jsonObject.put("price", cartModel.getPrice());
                        jsonArray.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("user_id", userId);
                    jsonObject.put("orderItems", jsonArray);
                    jsonObject.put("total", totalAmountValue);
                    jsonObject.put("Content-Type", "application/json");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("CartFragment", "response: " + response);
                        try {
                            String status = response.getString("status");
                            if (status.equals("true")){
                                Toast.makeText(getContext(), "Order Placed", Toast.LENGTH_SHORT).show();
                                sqlLiteClass.deleteAllData(Constant.CART_TABLE_NAME);
                                cartModelList.clear();
                                cartAdapter.notifyDataSetChanged();
                                updateTotalAmount(0.00);

                                MainActivity.updateCartCount(0);

                                fragment = new HomeFragment();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();

                                Toast.makeText(getContext(), "Order Placed", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("CartFragment", "error: " + error);
                    }
                });

                requestQueue.add(jsonObjectRequest);


















            }
        });









        // Inflate the layout for this fragment
        return view;
    }



    public static void updateTotalAmount(double amount){

        if (amount == 0.00){
            totalAmountValue = 0.00;
        }

        totalAmount.setText("Total: " + "₹ " + amount);
    }

}