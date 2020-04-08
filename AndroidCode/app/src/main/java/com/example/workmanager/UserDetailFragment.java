package com.example.workmanager;

import android.app.ProgressDialog;
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

public class UserDetailFragment extends Fragment {
    TextView txtEmail,txtPhone,txtRole,txtFullname;
    EditText edtGroup;
    Button btnSave ;
    int userId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail,container,false);
        txtFullname = view.findViewById(R.id.txtFullName);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPhone = view.findViewById(R.id.txtPhone);
        txtRole = view.findViewById(R.id.txtRole);
        edtGroup = view.findViewById(R.id.edtGroup);
        btnSave = view.findViewById(R.id.btnSave);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.workmanager_preferences", Context.MODE_PRIVATE);
        String userRole = sharedPreferences.getString("userRole","");
        Bundle bundle = getArguments();
        if(bundle != null){
            userId = bundle.getInt("userId", 0);
        }
        loadUserProfile(userId);
        if(!userRole.equalsIgnoreCase(RoleConstant.ADMIN)){
            edtGroup.setEnabled(false);
            btnSave.setVisibility(View.GONE);
        }
        btnSave.setOnClickListener((v)->{
            String txtGroupId = edtGroup.getText().toString();
            Integer groupId = null;
            if(!txtGroupId.isEmpty()){
                groupId = Integer.parseInt(txtGroupId);
            }
            UpdateRequest request = new UpdateRequest(userId,txtFullname.getText().toString(),
                                                      txtEmail.getText().toString(),
                                                      txtPhone.getText().toString(),
                                                      txtRole.getText().toString());
            request.setGroupId(groupId);
            UserDAO userDAO = new UserDAO();
            userDAO.update(request, new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getActivity(), "Save success", Toast.LENGTH_SHORT).show();
                    }else{
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
                    Toast.makeText(getActivity(), "Save fail", Toast.LENGTH_SHORT).show();
                }
            });
        });
        return view;
    }

    private void loadUserProfile(int userId) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        UserDAO userDAO = new UserDAO();
        userDAO.getUserProfile(userId, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    if(response.code() == ResponseCodeConstant.OK) {
                        UserDTO userDTO = response.body().getData();
                        txtFullname.setText(userDTO.getFullName());
                        txtEmail.setText(userDTO.getEmail());
                        txtPhone.setText(userDTO.getPhone());
                        txtRole.setText(userDTO.getRoleName());
                        if(userDTO.getGroupId() != null){
                            edtGroup.setText(userDTO.getGroupId() + "");
                        }else{
                            edtGroup.setText("none");
                        }
                        progressDialog.dismiss();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), response.message(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }
}
