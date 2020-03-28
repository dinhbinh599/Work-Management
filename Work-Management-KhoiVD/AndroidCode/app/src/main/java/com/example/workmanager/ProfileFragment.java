package com.example.workmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.workmanager.constants.ResponseCodeConstant;
import com.example.workmanager.daos.UserDAO;
import com.example.workmanager.requests.UpdateRequest;
import com.example.workmanager.responses.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    EditText edtUsername,edtPassword,edtFullname,edtEmail;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        edtUsername = view.findViewById(R.id.edtUsername);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtFullname = view.findViewById(R.id.edtFullname);
        edtEmail = view.findViewById(R.id.edtEmail);
        Button btnSave = view.findViewById(R.id.btnSave);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.workmanager_preferences", Context.MODE_PRIVATE);
        Bundle bundle = getArguments();
        int userId = sharedPreferences.getInt("userId",0);
        edtUsername.setText(sharedPreferences.getString("username",""));
        edtPassword.setText(sharedPreferences.getString("password",""));
        edtFullname.setText(sharedPreferences.getString("fullname",""));
        edtEmail.setText(sharedPreferences.getString("email",""));
        btnSave.setOnClickListener((v)-> {
            boolean check = true;
            String username,password,fullname,email,validate = "";
            username = edtUsername.getText().toString();
            if(username.compareTo("") == 0){
                validate += "Username can't be empty\n" ;
                check = false;
            }
            password = edtPassword.getText().toString();
            if(password.compareTo("") == 0){
                validate += "Password can't be empty\n" ;
                check = false;
            }
            fullname = edtFullname.getText().toString();
            if(fullname.compareTo("") == 0){
                validate += "Fullname can't be empty\n" ;
                check = false;
            }
            email = edtEmail.getText().toString();
            if(email.compareTo("") == 0){
                validate += "Email can't be empty\n" ;
                check = false;
            }
            if(check) {
                UpdateRequest request = new UpdateRequest(userId, edtUsername.getText().toString(), edtPassword.getText().toString(),
                        edtFullname.getText().toString(), edtEmail.getText().toString());
                UserDAO userDAO = new UserDAO();
                userDAO.update(request, new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Save success", Toast.LENGTH_SHORT).show();
                            sharedPreferences.edit().putString("username", edtUsername.getText().toString()).commit();
                            sharedPreferences.edit().putString("password", edtPassword.getText().toString()).commit();
                            sharedPreferences.edit().putString("fullname", edtFullname.getText().toString()).commit();
                            sharedPreferences.edit().putString("email", edtEmail.getText().toString()).commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), "Save fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }else Toast.makeText(getActivity(), validate, Toast.LENGTH_SHORT).show();
        });
        return view;
    }
}
