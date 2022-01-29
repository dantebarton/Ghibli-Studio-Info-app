package com.example.ghiblistudioinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MovieActivity extends AppCompatActivity {

    public static final String URL = "http://10.0.0.12:6400";

    private RequestQueue QUEUE;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_activity);

        QUEUE = Volley.newRequestQueue(this);
        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new imageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getApplicationContext(), FilmDetails.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out:
                /*try {
                    signOut(getApplicationContext());
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            case R.id.setting:
                Intent settingIntent = new Intent(this, settings.class);
                this.startActivity(settingIntent);
                break;
            default:
                Toast.makeText(getApplicationContext(),"Failed!",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void signOut(Context context)throws JSONException {
        final String MY_TOKEN = "o8xXjfRAgqHaK3HlHETeeiiYRhgjp0xYZIfphGfs4RNyTfxOynJbsBxGxNEPr3T5QYkY8C65TTwMplzRqJDFwinIp5zvgJrkeayDu1O38PD6Znz2GAuqDK0ToPND04cq";
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("method", "signOut");

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

}
