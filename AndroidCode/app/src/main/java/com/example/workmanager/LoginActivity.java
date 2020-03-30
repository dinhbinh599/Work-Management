package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workmanager.constants.ResponseCodeConstant;
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
        setContentView(R.layout.login_layout);
        edtUsername = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void clickToLogin(View view) {
        UserDAO userDAO = new UserDAO();
        LoginRequest request = new LoginRequest(edtUsername.getText().toString(), edtPassword.getText().toString());
        userDAO.checkLogin(request, new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == ResponseCodeConstant.OK) {
                    SharedPreferences sharedPreferences = getSharedPreferences("com.example.workmanager_preferences", MODE_PRIVATE);
                    sharedPreferences.edit().putInt("userId", response.body().getData().getUserId()).commit();
                    sharedPreferences.edit().putString("username",response.body().getData().getUsername()).commit();
                    sharedPreferences.edit().putString("password",response.body().getData().getPassword()).commit();
                    sharedPreferences.edit().putString("fullname",response.body().getData().getFullName()).commit();
                    sharedPreferences.edit().putString("email",response.body().getData().getEmail()).commit();
                    sharedPreferences.edit().putString("userRole", response.body().getData().getRoleName()).commit();
                    sharedPreferences.edit().putInt("groupId", response.body().getData().getGroupId() != null ? response.body().getData().getGroupId().intValue() : 0);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
    public void clickToRegisterPage(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
