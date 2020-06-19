package com.example.trabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

public class NoAccountActivity extends AppCompatActivity {

    private UserController userController;

    private SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

    private Button buttonSave;
    private ImageView buttonBack;

    private EditText inputUsername;
    private EditText inputName;
    private EditText inputBirth;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputConfirmPassword;

    private TextView erroUsername;
    private TextView erroName;
    private TextView erroBirth;
    private TextView erroEmail;
    private TextView erroPassword;
    private TextView erroConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_no_account);

        userController = new UserController(this);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonBack = (ImageView) findViewById(R.id.buttonBack);

        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputName = (EditText) findViewById(R.id.inputName);
        inputBirth = (EditText) findViewById(R.id.inputBirth);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputConfirmPassword = (EditText) findViewById(R.id.inputConfirmPassword);

        erroUsername = (TextView) findViewById(R.id.erroUsername);
        erroName = (TextView) findViewById(R.id.erroName);
        erroBirth = (TextView) findViewById(R.id.erroBirth);
        erroEmail = (TextView) findViewById(R.id.erroEmail);
        erroPassword = (TextView) findViewById(R.id.erroPassword);
        erroConfirmPassword = (TextView) findViewById(R.id.erroConfirmPassword);

        inputUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus) {
                    validaUsername();
                }

            }
        });

        inputName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus) {
                    validaName();
                }

            }
        });

        inputBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus) {
                        validaBirth();
                }
            }
        });

        inputEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus) {
                        validaEmail();
                }
            }
        });

        inputPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus) {
                       validaPassword();
                }
            }
        });

        inputConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {

                if(!hasFocus) {
                    validaConfirmPassword();
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validaCampos()){

                    try {

                        User user = new User();

                        user.setUsername(inputUsername.getText().toString());
                        user.setName(inputUsername.getText().toString());
                        user.setBirth(formater.parse(inputBirth.getText().toString()));
                        user.setEmail(inputEmail.getText().toString());
                        user.setPassword(inputPassword.getText().toString());

                        if(userController.create(user) > 0){

                            Intent loginIntent = new Intent(NoAccountActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        };

                    }catch (Exception e){

                        Toast.makeText(NoAccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                };
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(NoAccountActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }

    private boolean validaUsername(){

        erroUsername.setText("");
        if(inputUsername.getText().toString().equals("")){
            erroUsername.setText("Required");
            return false;
        }

        List<User> users = userController.readAll();

        for (User user : users){
            if (user.getUsername().equals(inputUsername.getText().toString())){
                erroUsername.setText("Username already in use");
                return false;
            };
        };

        return true;
    }

    private boolean validaName(){

        erroName.setText("");
        if(inputName.getText().toString().equals("")){
            erroName.setText("Required");
            return false;
        }

        return true;
    }

    private boolean validaBirth(){

        erroBirth.setText("");
        if(inputBirth.getText().toString().equals("")){

            erroBirth.setText("Required");
            return false;
        };

        try {

            formater.format(formater.parse(inputBirth.getText().toString()));
        } catch (Exception e) {

            erroBirth.setText("Invalid date (dd/MM/yyyy)");
            return false;
        };
        return true;
    }

    private boolean validaEmail(){

        erroEmail.setText("");
        if(inputEmail.getText().toString().equals("")){
            erroEmail.setText("Required");
            return false;
        };
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(inputEmail.getText().toString()).matches()) {
            erroEmail.setText("Invalid email");
            return false;
        };
        return true;
    }

    private boolean validaPassword(){
        erroPassword.setText("");
        if(inputPassword.getText().toString().equals("")){
            erroPassword.setText("Required");
            return false;
        };
        if (inputPassword.getText().toString().length() < 5) {

            erroPassword.setText("Invalid password, min length 5");
            return false;
        };
        return true;
    }

    private boolean validaConfirmPassword(){

        erroConfirmPassword.setText("");
        if (inputConfirmPassword.getText().toString().equals(inputPassword.getText().toString())){
            return true;
        };

        erroConfirmPassword.setText("Passwords don't match");
        return false;
    }

    private  boolean validaCampos(){
        boolean auxReturn = true;

        if (!validaUsername()){
            auxReturn = false;
        };
        if (!validaName()){
            auxReturn = false;
        };
        if (!validaBirth()){
            auxReturn = false;
        };
        if (!validaEmail()){
            auxReturn = false;
        };
        if (!validaPassword()){
            auxReturn = false;
        };
        if (!validaConfirmPassword()){
            auxReturn = false;
        };

        return auxReturn;
    }
}
