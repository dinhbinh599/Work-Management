package com.example.workmanager;

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

import com.example.workmanager.daos.GroupDAO;
import com.example.workmanager.requests.CreateGroupRequest;
import com.example.workmanager.responses.GroupResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupCreationFragment extends Fragment {
    Button btnCreate;
    EditText edtName, edtDescription;
    int userId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_group_creation, container,false);
        btnCreate = view.findViewById(R.id.btnCreate);
        edtName = view.findViewById(R.id.edtGroupName);
        edtDescription = view.findViewById(R.id.edtDescription);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.workmanager_preferences", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId",0);
        btnCreate.setOnClickListener((v)-> {
            boolean check = true;
            String validate = "";
            String groupName = edtName.getText().toString();
            if(groupName.equalsIgnoreCase("")){
                check = false;
                validate += "Group name can't be empty";
            }
            if(check){
                GroupDAO dao = new GroupDAO();
                CreateGroupRequest request = new CreateGroupRequest(userId,groupName,edtDescription.getText().toString());
                dao.createGroup(request, new Callback<GroupResponse>() {
                    @Override
                    public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Create success", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new GroupFragment()).commit();
                        }else{
                            Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GroupResponse> call, Throwable t) {

                    }
                });
            }else{
                Toast.makeText(getActivity(),validate,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
