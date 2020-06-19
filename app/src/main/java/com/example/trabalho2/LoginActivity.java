package com.example.trabalho2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private UserController controller;

    private Button buttonLogin;
    private Button buttonNoAccount;
    private Button buttonForgotPassword;
    private Button buttonClear;

    private TextView erroUsername;
    private TextView erroPassword;
    private TextView erroLogin;

    private EditText inputUsername;
    private EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        controller = new UserController(this);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonNoAccount = (Button) findViewById(R.id.buttonNoAccount);
        buttonForgotPassword = (Button) findViewById(R.id.buttonForgotPassword);
        buttonClear = (Button) findViewById(R.id.buttonClear);

        erroUsername = (TextView) findViewById(R.id.erroUsername);
        erroPassword = (TextView) findViewById(R.id.erroPassword);
        erroLogin = (TextView) findViewById(R.id.erroLogin);

        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                erroLogin.setText("");
                erroUsername.setText("");
                erroPassword.setText("");

                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();

                if(username.equals("") || password.equals("")){
                    if(username.equals("")){
                        erroUsername.setText("Field required!");
                    }
                    if(password.equals("")){
                        erroPassword.setText("Field required!");
                    }
                }else{
                    Long user_id = controller.login(username, password);
                    if(user_id != -1){
                        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);

                        homeIntent.putExtra("user_id", user_id);

                        startActivity(homeIntent);
                        finish();
                    }else{
                        erroLogin.setText("Account not found");
                    }
                }



            }
        });

        buttonNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent noAccountIntent = new Intent(LoginActivity.this, NoAccountActivity.class);
                startActivity(noAccountIntent);
                finish();
            }
        });

        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent forgotPasswordIntent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPasswordIntent);
                finish();
            }
        });


        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                controller.clear();
            }
        });
    }
}
