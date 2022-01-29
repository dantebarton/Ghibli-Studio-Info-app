package com.example.ghiblistudioinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterAccount extends AppCompatActivity {
    public static final String URL = "http://10.0.0.12:6400";
    private RequestQueue QUEUE;
    EditText temp;
    Button register;
    String password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        temp = findViewById(R.id.tempPassword);
        register = findViewById(R.id.register);
        QUEUE = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            password = extras.getString(newUserActivity.PASS);
            email = extras.getString(newUserActivity.EMAIL);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String temp_password = temp.getText().toString();

                try {
                    registerAccount(getApplicationContext(), password, temp_password,email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Registered!", Toast.LENGTH_SHORT).show();
                toMain(view);
            }
        });
    }

    public void registerAccount(Context context, String password, String tempPassword, String email)throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "registerAccount");
        jsonBody.put("password", password);
        jsonBody.put("temp_password", tempPassword);
        jsonBody.put("email", email);

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, (e)->{}, (e)->{});

        QUEUE.add(jsonPostRequest);
    }

    public void toMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}