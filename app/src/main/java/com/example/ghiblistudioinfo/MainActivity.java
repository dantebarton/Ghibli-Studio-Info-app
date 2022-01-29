package com.example.ghiblistudioinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "http://10.0.0.12:6400";

    private RequestQueue QUEUE;
    Button newUser, logIn;
    EditText email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newUser = findViewById(R.id.newUser);
        logIn = findViewById(R.id.log_in);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        QUEUE = Volley.newRequestQueue(this);

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNewUser(view);
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    signIn(getApplicationContext(), email.getText().toString(), password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                toFilms(view);
            }
        });
    }

    public void signIn(Context context, String email, String password)throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("password", password);
        jsonBody.put("time_span", 1);
        jsonBody.put("method", "authenticate");
        jsonBody.put("time_unit", "DAYS");
        jsonBody.put("email", email);

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, (e)->{}, (e)->{});

        QUEUE.add(jsonPostRequest);
    }

    public void toNewUser(View view){
        Intent intent = new Intent(this,newUserActivity.class );
        startActivity(intent);
    }

    public void toFilms(View view){
        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
    }
}