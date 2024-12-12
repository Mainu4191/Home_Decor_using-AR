package com.example.arapp3.Fragment;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    EditText email, password, name, phone, address, confirmPassword;
    Button btnRegister;
    TextView tvLogin;

    Fragment fragment;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register, container, false);
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        fragment = new LoginFragment();
        email = view.findViewById(R.id.etEmail);
        password = view.findViewById(R.id.etPassword);
        name = view.findViewById(R.id.etName);
        phone = view.findViewById(R.id.etPhone);
        address = view.findViewById(R.id.etAddress);
        confirmPassword = view.findViewById(R.id.etConfirmPassword);
        btnRegister = view.findViewById(R.id.btnRegister);
        tvLogin = view.findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                String getName = name.getText().toString();
                String getPhone = phone.getText().toString();
                String getAddress = address.getText().toString();
                String getConfirmPassword = confirmPassword.getText().toString();

                if (getName.isEmpty()) {
                    name.setError("Please enter your name");
                    name.requestFocus();
                    return;
                }

                if (getEmail.isEmpty()) {
                    email.setError("Please enter your email");
                    email.requestFocus();
                    return;
                }

                if (getPassword.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                    return;
                }

                if (getConfirmPassword.isEmpty()) {
                    confirmPassword.setError("Please enter your password");
                    confirmPassword.requestFocus();
                    return;
                }

                if (getPhone.isEmpty()) {
                    phone.setError("Please enter your phone number");
                    phone.requestFocus();
                    return;
                }

                if (getAddress.isEmpty()) {
                    address.setError("Please enter your address");
                    address.requestFocus();
                    return;
                }

                if (!getPassword.equals(getConfirmPassword)) {
                    confirmPassword.setError("Password does not match");
                    confirmPassword.requestFocus();
                    return;
                }

                if (getPassword.length() < 6) {
                    password.setError("Password must be at least 6 characters");
                    password.requestFocus();
                    return;
                }

                if (getPhone.length() < 10) {
                    phone.setError("Phone number must be at least 10 characters");
                    phone.requestFocus();
                    return;
                }
                //-->Send to Spring Boot

                String mainUrl = Constant.BASE_URL + "/users/signup";
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

                JSONObject postDate = new JSONObject();
                try {
                    postDate.put("email", getEmail);
                    postDate.put("password", getPassword);
                    postDate.put("name", getName);
                    postDate.put("phone", getPhone);
                    postDate.put("address", getAddress);
                    postDate.put("Content-Type", "application/json");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        mainUrl,
                        postDate,
                        response -> {

                            try {
                                if (response.getString("status").equals("true")) {

                                    Toast.makeText(getActivity().getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                                    //getParentFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                                } else {
                                    email.setError("Email already exist");
                                    email.requestFocus();
                                }
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }



                            },
                        error -> {
                            Log.d("Error.Response", error.toString());
                });

                requestQueue.add(jsonObjectRequest);








            }
    });

        tvLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
            }
        });
        return view;
    }
}