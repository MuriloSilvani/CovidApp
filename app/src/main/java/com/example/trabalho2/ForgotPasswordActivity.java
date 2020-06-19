package com.example.trabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ForgotPasswordActivity extends AppCompatActivity {

    private ImageView buttonBack;

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot_password);

        lista = (ListView) findViewById(R.id.listaPeso);

        this.listarPessoas();

        buttonBack = (ImageView) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }

    public void listarPessoas(){

        UserController controller = new UserController(this);
        List<User> users = controller.readAll();

        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        lista.setAdapter(adapter);
    }
}
