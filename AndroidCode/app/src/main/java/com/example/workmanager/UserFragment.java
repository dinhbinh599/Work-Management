package com.example.workmanager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.adapters.UserAdapter;
import com.example.workmanager.constants.ResponseCodeConstant;
import com.example.workmanager.daos.UserDAO;
import com.example.workmanager.dtos.UserDTO;
import com.example.workmanager.requests.GetUserRequest;
import com.example.workmanager.responses.GetUserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment {
    EditText txtSearch;
    Button btnSearch;
    RecyclerView wgRecyclerView;
    UserAdapter userAdapter;
    List<UserDTO> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        txtSearch = (EditText)view.findViewById(R.id.txtSearch);
        btnSearch = (Button)view.findViewById(R.id.btnSearch);
        wgRecyclerView = (RecyclerView)view.findViewById(R.id.wgRecyclerView);
        wgRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadAllUser();
        btnSearch.setOnClickListener((v)-> {
            String name = txtSearch.getText().toString();
            searchUser(name);
        });
        return view;
    }

    private void searchUser(String name){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        UserDAO userDAO = new UserDAO();
        GetUserRequest request = new GetUserRequest();
        userDAO.getAllUser(request, new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if(response.isSuccessful()){
                    if(response.code() == ResponseCodeConstant.OK){
                        userList = response.body().getData();
                        List<UserDTO> search = new ArrayList<>();
                        for (int i = 0; i < userList.size(); i++) {
                            if(userList.get(i).getFullName().contains(name)){
                                search.add(userList.get(i));
                            }
                        }
                        Toast.makeText(getActivity(),"count:" + search.size(), Toast.LENGTH_SHORT).show();
                        userAdapter = new UserAdapter(search);
                        wgRecyclerView.setAdapter(userAdapter);
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }

    private void loadAllUser(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        UserDAO userDAO = new UserDAO();
        GetUserRequest request = new GetUserRequest();
        userDAO.getAllUser(request, new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if(response.isSuccessful()){
                    if(response.code() == ResponseCodeConstant.OK){
                        userList = response.body().getData();
                        userAdapter = new UserAdapter(userList);
                        wgRecyclerView.setAdapter(userAdapter);
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }
}
