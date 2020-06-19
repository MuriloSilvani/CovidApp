package com.example.trabalho2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private DiagnosticController diagnosticController;

    private ImageView buttonBack;

    private Button buttonEnviar;

    private int pontuacao = 0;
    private List sintomas = new ArrayList();

    private LocationManager locationManager;
    private LocationListener listener;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_quiz);

        Bundle params = getIntent().getExtras();
        final Long user_id = params.getLong("user_id");

        diagnosticController = new DiagnosticController(this);

        buttonBack = (ImageView) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeIntent = new Intent(QuizActivity.this, HomeActivity.class);
                homeIntent.putExtra("user_id", user_id);
                startActivity(homeIntent);
                finish();
            }
        });

        buttonEnviar = (Button) findViewById(R.id.buttonEnviar);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                CheckBox checkBox;
                boolean checked;
                pontuacao = 0;
                sintomas.clear();

                checkBox = findViewById(R.id.checkbox1);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 1;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox2);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 1;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox3);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 1;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox4);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 5;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox5);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 5;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox6);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 5;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox7);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 5;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox8);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 5;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox9);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 5;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox10);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 10;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox11);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 10;
                    sintomas.add(checkBox.getText().toString());
                }

                checkBox = findViewById(R.id.checkbox12);
                checked = ((CheckBox) checkBox).isChecked();
                if (checked) {
                    pontuacao += 10;
                    sintomas.add(checkBox.getText().toString());
                }


                // pontuacao

                if(pontuacao <= 10){
                    // fique em casa
                }
                if(pontuacao > 10 && pontuacao <= 25){
                    // Ligue para disque corona e se informe se é necessário ir até um local de atendimento
                }
                if(pontuacao > 25){
                    // Ao salvar o formulario, o número cadastrado receberá uma ligação de um disque corona
                    // a qual um atendente falará com quem apresenta os sintomas orientando para que se desloque até um centro de atendimento

                }

                Diagnostic diagnostic = new Diagnostic();
                diagnostic.setDiagnostic(pontuacao);
                diagnostic.setLatitude(latitude);
                diagnostic.setLongitude(longitude);
                diagnostic.setUser_id(user_id);

                diagnosticController.create(diagnostic);

                buttonBack.callOnClick();
            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}, 10);
            }
            return;
        }
        locationManager.requestLocationUpdates("gps", 5000, 0, listener);
    }
}
