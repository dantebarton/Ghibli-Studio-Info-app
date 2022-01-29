package com.example.ghiblistudioinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class newUserActivity extends AppCompatActivity {
    public static final String PASS = "com.example.ghiblistudioinfo.PASS";
    public static final String EMAIL = "com.example.ghiblistudioinfo.EMAIL";
    public static final String URL = "http://10.0.0.12:6400";

    private RequestQueue QUEUE;
    EditText firstName, lastName, password, email;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        firstName = findViewById(R.id.firstNameText);
        lastName = findViewById(R.id.lastNameText);
        password = findViewById(R.id.userPassword);
        email = findViewById(R.id.userEmail);
        register = findViewById(R.id.registerButton);
        QUEUE = Volley.newRequestQueue(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String first_name = firstName.getText().toString();
                final String last_name = lastName.getText().toString();
                final String mail = email.getText().toString();
                final String pass = password.getText().toString();

                try {
                    createAccount(getApplicationContext(), last_name, first_name,mail, pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                toRegisterAccount(view);
            }
        });

    }

    public void createAccount(Context context, String lastname, String firstname, String email, String password) throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "createAccount");
        jsonBody.put("last_name", lastname);
        jsonBody.put("first_name", firstname);
        jsonBody.put("email", email);
        jsonBody.put("password", password);

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, (e)->{}, (e)->{});

        QUEUE.add(jsonPostRequest);
    }

    public void toRegisterAccount(View view){
        Intent intent = new Intent(this, RegisterAccount.class);
        String pass = password.getText().toString();
        String mail = email.getText().toString();
        intent.putExtra(PASS, pass);
        intent.putExtra(EMAIL, mail);
        startActivity(intent);
    }
}
