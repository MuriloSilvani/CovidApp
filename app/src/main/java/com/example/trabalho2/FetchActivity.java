package com.example.trabalho2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchActivity extends AppCompatActivity {

    private ImageView buttonBack;

    private Button buttonMap;

    private TextView textLocal, textCasos, textMortes, textSuspeitos, textCurados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_fetch);

        Bundle params = getIntent().getExtras();
        final Long user_id = params.getLong("user_id");

        buttonBack = (ImageView) findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent homeIntent = new Intent(FetchActivity.this, HomeActivity.class);
                homeIntent.putExtra("user_id", user_id);
                startActivity(homeIntent);
                finish();
            }
        });

        buttonMap = (Button) findViewById(R.id.buttonMap);

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent statsIntent = new Intent(FetchActivity.this, MapsActivity.class);
                statsIntent.putExtra("user_id", user_id);
                startActivity(statsIntent);
            }
        });

        textLocal = (TextView) findViewById(R.id.textLocal);
        textCasos = (TextView) findViewById(R.id.textCasos);
        textMortes = (TextView) findViewById(R.id.textMortes);
        textSuspeitos = (TextView) findViewById(R.id.textSuspeitos);
        textCurados = (TextView) findViewById(R.id.textCurados);

        fetchdata();

    }

    private void fetchdata(){

        String url = "https://covid19-brazil-api.now.sh/api/report/v1";

        StringRequest request
                = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        try {

                            JSONObject data = new JSONObject(response);
                            JSONArray array = data.getJSONArray("data");

                            JSONObject item = new JSONObject();
                            for (int i = 0; i < array.length(); i++){
                                item = array.getJSONObject(i);

                            }

                            textLocal.setText(item.getString("state") + " - " + item.getString("uf"));
                            textCasos.setText(item.getString("cases"));
                            textMortes.setText(item.getString("deaths"));
                            textSuspeitos.setText(item.getString("suspects"));
                            textCurados.setText(item.getString("refuses"));

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(FetchActivity.this, "Exception", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(FetchActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
