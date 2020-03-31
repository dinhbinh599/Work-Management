package com.example.workmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.adapters.GroupAdapter;
import com.example.workmanager.daos.GroupDAO;
import com.example.workmanager.dtos.GroupDTO;
import com.example.workmanager.dtos.UserDTO;
import com.example.workmanager.responses.GetGroupResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupFragment extends Fragment {
    RecyclerView recyclerView;
    GroupAdapter groupAdapter;
    List<UserDTO> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        recyclerView = view.findViewById(R.id.wgRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.workmanager_preferences", Context.MODE_PRIVATE);
        String userRole = sharedPreferences.getString("userRole","");
        if(userRole.equalsIgnoreCase("Admin")){
            loadAllGroup();
        }
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
                    Toast.makeText(getActivity(), groupList.get(0).getGroupId() + "",Toast.LENGTH_LONG).show();
                    recyclerView.setAdapter(groupAdapter);
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(),groupList.size() + "",Toast.LENGTH_SHORT).show();
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
