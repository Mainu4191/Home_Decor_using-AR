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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.arapp3.Adapter.OrderDetailsAdapter;
import com.example.arapp3.Constant;
import com.example.arapp3.Model.OrderDetails;
import com.example.arapp3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDetailsFragment extends Fragment {


    RequestQueue requestQueue;

    List<OrderDetails> orderDetailsList;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    TextView tv_order_id, tv_total_items, tv_total_price;





    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderDetailsFragment newInstance(String param1, String param2) {
        OrderDetailsFragment fragment = new OrderDetailsFragment();
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

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_details, container, false);


        recyclerView = view.findViewById(R.id.rv_order_details);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        tv_order_id = view.findViewById(R.id.tv_order_id);
        tv_total_items = view.findViewById(R.id.tv_total_items);
        tv_total_price = view.findViewById(R.id.tv_total_price);


        Bundle bundle = this.getArguments();

        long orderId = bundle.getLong("order_id");
        Double orderTotal = bundle.getDouble("order_total");
        int totalItems = bundle.getInt("total_items");

        tv_order_id.setText(String.valueOf(orderId));
        tv_total_items.setText(String.valueOf(totalItems));
        tv_total_price.setText(String.valueOf(orderTotal));

        String Url = Constant.BASE_URL + "/users/api/order/get-order-details-by-id?id=" + orderId;
        requestQueue = Volley.newRequestQueue(getContext());


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Url, response -> {
            Log.d("TAG", "onCreateView: Response:- " + response);
            orderDetailsList = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                try {
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setName(response.getJSONObject(i).getString("name"));
                    orderDetails.setImage(response.getJSONObject(i).getString("image"));
                    orderDetails.setPrice(response.getJSONObject(i).getInt("price"));
                    orderDetails.setQuantity(response.getJSONObject(i).getInt("quantity"));
                    orderDetails.setFolderName(response.getJSONObject(i).getString("folderName"));

                    orderDetailsList.add(orderDetails);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            adapter = new OrderDetailsAdapter(orderDetailsList, getContext());
            recyclerView.setAdapter(adapter);

        }, error -> {
            Log.d("TAG", "onCreateView: Error:- " + error);
        });

        requestQueue.add(jsonArrayRequest);







        return view;
    }
}