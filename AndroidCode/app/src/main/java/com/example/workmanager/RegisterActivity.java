package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workmanager.constants.ResponseCodeConstant;
import com.example.workmanager.daos.UserDAO;
import com.example.workmanager.requests.RegisterRequest;
import com.example.workmanager.responses.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtUsername,edtPassword,editConfirmPass,
                     edtFullname,edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        edtUsername = (EditText)findViewById(R.id.edtUsername);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtFullname = (EditText)findViewById(R.id.edtFullname);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
    }

    public void clickToRegister(View view){
        RegisterRequest request = new RegisterRequest(edtUsername.getText().toString(),edtPassword.getText().toString(),
                                     edtFullname.getText().toString(),edtEmail.getText().toString(),1,1);
        UserDAO userDAO = new UserDAO();
        userDAO.register(request, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.code() == ResponseCodeConstant.OK) {
                    Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Register fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
