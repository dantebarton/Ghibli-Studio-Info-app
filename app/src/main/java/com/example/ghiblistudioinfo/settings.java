package com.example.ghiblistudioinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class settings extends AppCompatActivity {

    public static final String URL = "http://10.0.0.12:6400";

    private RequestQueue QUEUE;
    EditText firstNameUpdate, lastNameUpdate;
    Button update;
    Map<String, String> name = new HashMap<>();
    String firstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        QUEUE = Volley.newRequestQueue(this);
        firstNameUpdate = findViewById(R.id.firstNameUpdate);
        lastNameUpdate = findViewById(R.id.lastNameUpdate);
        update = findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = firstNameUpdate.getText().toString();
                Log.d("On Click: ", firstName);
                name.put("first_name", firstName);
                name.put("last_name", lastNameUpdate.getText().toString());

                /*try {
                    upDate(getApplicationContext(),name);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                Toast.makeText(getApplicationContext(), "Update successful!", Toast.LENGTH_SHORT).show();
                toFilms(view);
            }
        });


    }

    public void upDate(Context context, Map<String, String> updateNames) throws JSONException {
        final String MY_TOKEN = "o8xXjfRAgqHaK3HlHETeeiiYRhgjp0xYZIfphGfs4RNyTfxOynJbsBxGxNEPr3T5QYkY8C65TTwMplzRqJDFwinIp5zvgJrkeayDu1O38PD6Znz2GAuqDK0ToPND04cq";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "setAccount");
        jsonBody.put("account", updateNames);

        JsonObjectRequest jsonPostRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, (e)->{}, (e)->{}){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();

                headers.put("autho_token", MY_TOKEN);
                return headers;
            }
        };

        QUEUE.add(jsonPostRequest);
    }

    public void toFilms(View view){
        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
    }
}