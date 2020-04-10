package com.example.workmanager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workmanager.adapters.UserAdapter;
import com.example.workmanager.constants.ResponseCodeConstant;
import com.example.workmanager.daos.GroupDAO;
import com.example.workmanager.daos.UserDAO;
import com.example.workmanager.dtos.GroupDTO;
import com.example.workmanager.dtos.UserDTO;
import com.example.workmanager.requests.GetUserRequest;
import com.example.workmanager.requests.UpdateRequest;
import com.example.workmanager.responses.GetUserResponse;
import com.example.workmanager.responses.GroupResponse;
import com.example.workmanager.responses.UserResponse;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupDetailFragment extends Fragment {
    TextView txtName, txtDescription, txtCreatedTime;
    int groupId;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<UserDTO> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_detail, container, false);
        txtName = view.findViewById(R.id.txtGroupName);
        txtDescription = view.findViewById(R.id.txtDescription);
        txtCreatedTime = view.findViewById(R.id.txtCreatedTime);
        recyclerView = view.findViewById(R.id.wgRecyclerView);
        Button btnDelete = view.findViewById(R.id.btnDeleteGroup);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Bundle bundle = getArguments();
        if((bundle != null)){
            groupId = bundle.getInt("groupId",0);
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
            loadGroupDetail(groupId);
            loadAllUserInGroup(groupId);
            progressDialog.dismiss();
        }
        btnDelete.setOnClickListener((v)->{
            GroupDAO groupDAO = new GroupDAO();
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Warning");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage("Are you sure to delete, there maybe some user in group");
            builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                    progressDialog.show();
                    removeGroupIdInUser(groupId);
                    groupDAO.deleteGroup(groupId, new Callback<GroupResponse>() {
                        @Override
                        public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                            if(response.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(),"Delete success",Toast.LENGTH_LONG);
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new GroupFragment()).commit();
                            }
                        }

                        @Override
                        public void onFailure(Call<GroupResponse> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(),"Delete fail",Toast.LENGTH_LONG);
                            t.printStackTrace();
                        }
                    });
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        });
        return view;
    }

    private void loadGroupDetail(int groupId){
        GroupDAO groupDAO = new GroupDAO();
        groupDAO.getGroupDetail(groupId, new Callback<GroupResponse>() {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                if(response.isSuccessful()) {
                    GroupDTO groupDTO = response.body().getData();
                    txtName.setText(groupDTO.getName());
                    txtDescription.setText(groupDTO.getDescription());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    txtCreatedTime.setText(sdf.format(groupDTO.getCreatedTime()));
                }else{
                    Toast.makeText(getActivity(),response.message(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
            }
        });
    }

    private void loadAllUserInGroup(int groupId){
        UserDAO userDAO = new UserDAO();
        GetUserRequest request = new GetUserRequest();
        request.setGroupId(groupId);
        userDAO.getAllUser(request, new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if(response.isSuccessful()){
                    if(response.code() == ResponseCodeConstant.OK){
                        userList = response.body().getData();
                        userAdapter = new UserAdapter(userList);
                        recyclerView.setAdapter(userAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {

            }
        });
    }

    private void removeGroupIdInUser(int groupId){
        UserDAO userDAO = new UserDAO();
        GetUserRequest request = new GetUserRequest();
        request.setGroupId(groupId);
        userDAO.getAllUser(request, new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if(response.isSuccessful()){
                    if(response.code() == ResponseCodeConstant.OK){
                        userList = response.body().getData();
                        if(userList != null) {
                            for (int i = 0; i < userList.size(); i++) {
                                removeGroupId(userList.get(i));
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {

            }
        });
    }

    private void removeGroupId(UserDTO userDTO){
        UserDAO userDAO = new UserDAO();
        UpdateRequest request = new UpdateRequest(userDTO.getUserId(),userDTO.getFullName(),userDTO.getEmail(),
                                                  userDTO.getPhone(),userDTO.getRoleName());
        request.setGroupId(null);
        userDAO.update(request, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(!response.isSuccessful()){
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
                t.printStackTrace();
            }
        });
    }
}
