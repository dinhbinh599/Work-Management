package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.workmanager.daos.UserDAO;
import com.example.workmanager.requests.LoginRequest;
import com.example.workmanager.responses.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsername = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void clickToLogin(View view) {
        UserDAO userDAO = new UserDAO();
        LoginRequest request = new LoginRequest(edtUsername.getText().toString(), edtPassword.getText().toString());
        userDAO.checkLogin(request, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}
