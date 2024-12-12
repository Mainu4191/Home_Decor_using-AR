package com.example.arapp3.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arapp3.Constant;
import com.example.arapp3.R;
import com.example.arapp3.Util.Session;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    EditText email, password;
    Button btnLogin;
    TextView tvRegister;
    Fragment fragment;

    Session session;

    NavigationView navigationView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        fragment = new RegisterFragment();
        email = view.findViewById(R.id.etEmail);
        password = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvRegister = view.findViewById(R.id.tvRegister);
        navigationView = view.findViewById(R.id.navMenu);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                getParentFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();


        }
        }
        );


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailInput = email.getText().toString();
                String passwordInput = password.getText().toString();

                if(emailInput.isEmpty()){
                    email.setError("Please enter your email");
                    email.requestFocus();
                    return;
                }

                if(passwordInput.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                    return;
                }

                //--> Send data to server

                String makeUrl = Constant.BASE_URL + "/users/login";
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

                JSONObject postData = new JSONObject();
                try {
                    postData.put("email", emailInput);
                    postData.put("password", passwordInput);
                    postData.put("Content-Type", "application/json");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        makeUrl,
                        postData,
                        response -> {
                            System.out.println(response);

                            try {
                                if (response.getString("status").equals("true")) {

                                    session = new Session(getActivity().getApplicationContext());
                                    session.setLoggedIn(true);

                                    Log.d("TAG", response.getString("userId"));
                                    session.setUserId(response.getString("userId"));


                                    NavigationView navigationView = getActivity().findViewById(R.id.navMenu);
                                    navigationView.getMenu().clear();
                                    navigationView.inflateMenu(R.menu.loginmenu);



                                    Fragment fragment = new HomeFragment();
                                    getParentFragmentManager().beginTransaction().replace(R.id.main_container,fragment).commit();





                                    Toast.makeText(getActivity().getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                    email.setError("Please enter your email");
                                    email.requestFocus();
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        error -> {
                            Log.d("Error.Response", error.toString());
                        }
                );

                requestQueue.add(jsonObjectRequest);




            }
        }
        );



        return view;
    }
}