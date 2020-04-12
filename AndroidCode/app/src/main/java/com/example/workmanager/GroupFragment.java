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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.adapters.GroupAdapter;
import com.example.workmanager.adapters.UserAdapter;
import com.example.workmanager.constants.ResponseCodeConstant;
import com.example.workmanager.constants.RoleConstant;
import com.example.workmanager.daos.GroupDAO;
import com.example.workmanager.daos.UserDAO;
import com.example.workmanager.dtos.GroupDTO;
import com.example.workmanager.dtos.UserDTO;
import com.example.workmanager.requests.GetUserRequest;
import com.example.workmanager.responses.GetGroupResponse;
import com.example.workmanager.responses.GetUserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupFragment extends Fragment {
    RecyclerView recyclerView;
    GroupAdapter groupAdapter;
    UserAdapter userAdapter;
    List<UserDTO> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        Button btnCreateGroup = view.findViewById(R.id.btnCreateGroup);
        Button btnSearch = view.findViewById(R.id.btnSearch);
        EditText edtSearch = view.findViewById(R.id.edtSearch);
        recyclerView = view.findViewById(R.id.wgRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.workmanager_preferences", Context.MODE_PRIVATE);
        String userRole = sharedPreferences.getString("userRole","");
        if(userRole.equalsIgnoreCase(RoleConstant.ADMIN)){
            loadAllGroup();
        }else{
            loadAllUserInGroup(sharedPreferences.getInt("groupId",0));
            edtSearch.setVisibility(View.GONE);
            btnCreateGroup.setVisibility(View.GONE);
            btnSearch.setVisibility(View.GONE);
        }
        btnCreateGroup.setOnClickListener((v)->{
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new GroupCreationFragment()).addToBackStack("groupFragment").commit();

        });
        btnSearch.setOnClickListener((v)->{
            String search = edtSearch.getText().toString();
            searchGroup(search);
        });
        return view;
    }

    private void loadAllGroup() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        GroupDAO groupDAO = new GroupDAO();
        groupDAO.getAllGroup(new Callback<GetGroupResponse>() {
            @Override
            public void onResponse(Call<GetGroupResponse> call, Response<GetGroupResponse> response) {

                if(response.isSuccessful()){
                    List<GroupDTO> groupList = response.body().getData();
                    groupAdapter = new GroupAdapter(groupList);
                    recyclerView.setAdapter(groupAdapter);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<GetGroupResponse> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }

    private void loadAllUserInGroup(int groupId) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        UserDAO userDAO = new UserDAO();
        GetUserRequest request = new GetUserRequest();
        request.setGroupId(groupId);
        userDAO.getAllUser(request, new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if(response.isSuccessful()) {
                    if (response.code() == ResponseCodeConstant.OK) {
                        userList = response.body().getData();
                        if(userList.size() == 0){
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(),"You don't have group",Toast.LENGTH_SHORT).show();
                        }else{

                            userAdapter = new UserAdapter(userList);
                            recyclerView.setAdapter(userAdapter);
                            progressDialog.dismiss();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void searchGroup(String name) {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();
        GroupDAO groupDAO = new GroupDAO();
        groupDAO.getAllGroup(new Callback<GetGroupResponse>() {
            @Override
            public void onResponse(Call<GetGroupResponse> call, Response<GetGroupResponse> response) {

                if(response.isSuccessful()){
                    List<GroupDTO> groupList = response.body().getData();
                    List<GroupDTO> search = new ArrayList<>();
                    for (int i = 0; i < groupList.size(); i++) {
                        if(groupList.get(i).getName().contains(name)){
                            search.add(groupList.get(i));
                        }
                    }
                    Toast.makeText(getActivity(),"count:" + search.size(), Toast.LENGTH_SHORT).show();
                    groupAdapter = new GroupAdapter(search);
                    recyclerView.setAdapter(groupAdapter);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<GetGroupResponse> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
            }
        });
    }
}
