package com.example.arapp3.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arapp3.Adapter.ProductViewAdapter;
import com.example.arapp3.Constant;
import com.example.arapp3.Model.ProductList;
import com.example.arapp3.R;
import com.example.arapp3.Util.SqlLiteClass;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<ProductList> productList;
    RequestQueue requestQueue;

    String url = Constant.BASE_URL + "/users/api/product/get-all";
    // http://localhost:8080/users/api/product/get-all



    //SqlLiteClass For Cart Management
    public static  SqlLiteClass sqlLiteClass;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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


        sqlLiteClass = new SqlLiteClass();
        sqlLiteClass.createDatabase(getActivity().getApplicationContext(), "CartDB");


        sqlLiteClass.createTable("Cart", "id INTEGER PRIMARY KEY AUTOINCREMENT, product_id INTEGER UNIQUE, name VARCHAR, price VARCHAR, description TEXT, image VARCHAR, folderName VARCHAR, quantity INTEGER");






        productList = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.Product_recyclerView_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));

        Log.d(("HomeFragment"), "onCreateView:");
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        Log.d("HomeFragment", response.toString());
                        Log.d("HomeFragment", "onCreateView:");

                        //Make a list of product
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ProductList product = new ProductList();
                            product.setId(jsonObject.getInt("id"));
                            product.setName(jsonObject.getString("name"));
                            product.setPrice(jsonObject.getString("price"));
                            product.setDescription(jsonObject.getString("description"));
                            product.setImage(jsonObject.getString("image"));
                            product.setModel(jsonObject.getString("model"));
                            product.setModelBufferFile(jsonObject.getString("modelBufferFile"));
                            product.setFolderName(jsonObject.getString("folderName"));
                            productList.add(product);
                        }
                        for (int i = 0; i < productList.size(); i++) {
                            Log.d("HomeFragment", productList.get(i).getName());
                        }


                        adapter = new ProductViewAdapter(productList, getActivity().getApplicationContext());
                        recyclerView.setAdapter(adapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.d("HomeFragment", error.toString());
                    Log.d("HomeFragment", "onCreateView:");
                }
        );

        requestQueue.add(jsonArrayRequest);


        return view;
    }
}