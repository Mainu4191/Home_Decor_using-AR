package com.example.arapp3.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arapp3.Constant;
import com.example.arapp3.Model.UsersEntity;
import com.example.arapp3.R;
import com.example.arapp3.Util.Session;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    EditText etName, etEmail, etPhone, etAddress;
    EditText etOldPassword, etNewPassword, etConfirmPassword;

    Session session;

    RequestQueue requestQueue;

    Button btnUpdate;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etName = view.findViewById(R.id.et_user_name);
        etEmail = view.findViewById(R.id.et_email);
        etPhone = view.findViewById(R.id.et_mobile_number);
        etAddress = view.findViewById(R.id.et_address);
        etOldPassword = view.findViewById(R.id.et_old_password);
        etNewPassword = view.findViewById(R.id.et_new_password);
        etConfirmPassword = view.findViewById(R.id.et_confirm_password);
        btnUpdate = view.findViewById(R.id.btn_update);

        session = new Session(getContext());

        requestQueue = Volley.newRequestQueue(getContext());

        int user_id = Integer.parseInt(session.getUserId());

        String url = Constant.BASE_URL + "/users/api/user/profile?id=" + user_id;


        UsersEntity usersEntity = new UsersEntity();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                etName.setText(response.getString("name"));
                etEmail.setText(response.getString("email"));
                etPhone.setText(response.getString("phone"));
                etAddress.setText(response.getString("address"));

                usersEntity.setId(response.getInt("id"));
                usersEntity.setName(response.getString("name"));
                usersEntity.setEmail(response.getString("email"));
                usersEntity.setPhone(response.getString("phone"));
                usersEntity.setAddress(response.getString("address"));
                usersEntity.setPassword(response.getString("password"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
        });


        requestQueue.add(jsonObjectRequest);

        btnUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String address = etAddress.getText().toString();
            String oldPassword = etOldPassword.getText().toString();
            String newPassword = etNewPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            if (oldPassword.equals(usersEntity.getPassword())) {
                if (newPassword.equals(confirmPassword)) {




                    String updateUrl = Constant.BASE_URL + "/users/api/user/profile/update";

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("id", usersEntity.getId());
                        jsonObject.put("name", name);
                        jsonObject.put("email", email);
                        jsonObject.put("phone", phone);
                        jsonObject.put("address", address);
                        jsonObject.put("password", newPassword);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest updateRequest = new JsonObjectRequest(Request.Method.POST, updateUrl, jsonObject, response -> {
                        try {
                            if (response.getBoolean("status")) {
                                session.setUserName(name);
                                session.setUserEmail(email);
                                session.setUserPhone(phone);
                                session.setUserAddress(address);
                            }
                            Toast.makeText(getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }, Throwable::printStackTrace);

                    requestQueue.add(updateRequest);
                }
            }
        });









        return view;
    }
}