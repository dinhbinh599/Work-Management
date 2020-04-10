package com.example.workmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.workmanager.constants.RoleConstant;
import com.example.workmanager.daos.UserDAO;
import com.example.workmanager.dtos.UserDTO;
import com.example.workmanager.requests.UpdateRequest;
import com.example.workmanager.responses.UserResponse;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    EditText edtFullname,edtEmail,edtPhone;
    TextView txtRole;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        edtFullname = view.findViewById(R.id.edtFullname);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPhone = view.findViewById(R.id.edtPhone);
        txtRole = view.findViewById(R.id.txtRole);
        Button btnSave = view.findViewById(R.id.btnSave);

        Button btnLogOut = view.findViewById(R.id.btnLogOut);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.workmanager_preferences", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId",0);
        loadUserProfile(userId);
        btnLogOut.setOnClickListener((v)->{
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
        });

        btnSave.setOnClickListener((v)-> {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
            boolean check = true;
            String fullname,email,phone,role,validate = "";
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
            phone = edtPhone.getText().toString();
            role = txtRole.getText().toString();
            if(check) {
                UpdateRequest request = new UpdateRequest(userId,fullname,email,phone,role);
                UserDAO userDAO = new UserDAO();
                userDAO.update(request, new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Save success", Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.dismiss();
                            TypeAdapter<UserResponse> adapter = new Gson().getAdapter(UserResponse.class);
                            try{
                                if(response.errorBody() != null) {
                                    UserResponse userResponse = adapter.fromJson(response.errorBody().string());
                                    Toast.makeText(getActivity(), userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Save fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), validate, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void loadUserProfile (int userId) {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        UserDAO userDAO = new UserDAO();
        userDAO.getUserProfile(userId, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    UserDTO userDTO = response.body().getData();
                    edtEmail.setText(userDTO.getEmail());
                    edtFullname.setText(userDTO.getFullName());
                    edtPhone.setText(userDTO.getPhone());
                    txtRole.setText(userDTO.getRoleName());
                    progressDialog.dismiss();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
