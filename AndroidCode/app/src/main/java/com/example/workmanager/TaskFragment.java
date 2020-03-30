package com.example.workmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.workmanager.adapters.TaskAdapter;
import com.example.workmanager.constants.ResponseCodeConstant;
import com.example.workmanager.constants.RoleConstant;
import com.example.workmanager.daos.TaskDAO;
import com.example.workmanager.dtos.TaskDTO;
import com.example.workmanager.requests.GetTaskRequest;
import com.example.workmanager.responses.GetTaskResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskFragment extends Fragment {
    private List<TaskDTO> taskList;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    Button btnAddTask, btnFilter;
    Spinner spStatus;
    int userId;
    String userRole;

    public TaskFragment(){
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.example.workmanager_preferences", Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", 0);
        userRole = sharedPreferences.getString("userRole", null);

        //get recycle view from xml
        recyclerView = view.findViewById(R.id.wgRecyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        loadAllTask();
        btnAddTask = view.findViewById(R.id.btnAddTask);
        btnFilter = view.findViewById(R.id.btnFilter);
        return view;
    }

    public void loadAllTask(){
        try{
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
            TaskDAO taskDAO = new TaskDAO();
            GetTaskRequest request = new GetTaskRequest();
            request.setUserId(userId);
            request.setStatusId(0);
            if (RoleConstant.USER.equalsIgnoreCase(userRole)){
                request.setHandlerId(userId);
            }
            taskDAO.getAllTask(request, new Callback<GetTaskResponse>() {
                @Override
                public void onResponse(Call<GetTaskResponse> call, Response<GetTaskResponse> response) {
                    switch (response.code()) {
                        case ResponseCodeConstant.OK: {
                            taskList = response.body().getData();
                            System.out.println("Task List: " +taskList);
                            taskAdapter = new TaskAdapter(taskList);
                            recyclerView.setAdapter(taskAdapter);
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case ResponseCodeConstant.UNAUTHORIZED:{
                            Toast.makeText(getActivity(), "Unauthorized", Toast.LENGTH_SHORT).show();
                            break;
                    }
                            case ResponseCodeConstant.ERROR:
                                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                                break;
                        default:
                            break;
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<GetTaskResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    Log.d("Failure", t.getMessage());
                    progressDialog.dismiss();
                }
            });
        } catch (Exception e){
            Log.d("Task Fragment Exception", e.getMessage());
        }
    }
}
