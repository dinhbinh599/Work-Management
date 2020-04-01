package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.workmanager.constants.ResponseCodeConstant;
import com.example.workmanager.daos.UserDAO;
import com.example.workmanager.requests.RegisterRequest;
import com.example.workmanager.responses.UserResponse;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtUsername,edtPassword,edtConfirmPass,
                     edtFullname,edtEmail,edtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        edtUsername = (EditText)findViewById(R.id.edtUsername);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtConfirmPass = (EditText)findViewById(R.id.edtConfirmPass);
        edtFullname = (EditText)findViewById(R.id.edtFullname);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtPhone = (EditText)findViewById(R.id.edtPhone);
    }

    public void clickToLoginPage(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void clickToRegister(View view){
        boolean check = true;
        String username,password,fullname,email,phone,validate = "";
        username = edtUsername.getText().toString();
        if(username.compareTo("") == 0){
            validate += "Username can't be empty\n" ;
            check = false;
        }
        password = edtPassword.getText().toString();
        if(password.compareTo("") == 0){
            validate += "Password can't be empty\n" ;
            check = false;
        }else{
            if(!password.equalsIgnoreCase(edtConfirmPass.getText().toString())){
                validate += "Password and ConfirmPass is not the same\n" ;
                check = false;
            }
        }
        fullname = edtFullname.getText().toString();
        if(fullname.compareTo("") == 0){
            validate += "Fullname can't be empty\n" ;
            check = false;
        }
        email = edtEmail.getText().toString();
        if(email.compareTo("") == 0){
            validate += "Email can't be empty\n" ;
            check = false;
        }
        phone = edtPhone.getText().toString();
        if(check) {
            RegisterRequest request = new RegisterRequest(username,password,fullname,email,phone);
            UserDAO userDAO = new UserDAO();
            userDAO.register(request, new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.code() == ResponseCodeConstant.OK) {
                        Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        TypeAdapter<UserResponse> adapter = new Gson().getAdapter(UserResponse.class);
                        try{
                            if(response.errorBody() != null) {
                                UserResponse userResponse = adapter.fromJson(response.errorBody().string());
                                Toast.makeText(RegisterActivity.this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Register fail", Toast.LENGTH_SHORT).show();
                }
            });
        }else Toast.makeText(RegisterActivity.this, validate, Toast.LENGTH_SHORT).show();
    }
}
