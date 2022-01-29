package com.example.ghiblistudioinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONException;
import org.json.JSONObject;

public class FilmDetails extends AppCompatActivity {

    YouTubePlayerView trailer;
    TextView filmTitle, originalTitle, romanisedTitle, director, producer, releaseDate, runTime, description;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);

        String url = "https://ghibliapi.herokuapp.com/films/";

        String[] movieId = {"2baf70d1-42bb-4437-b551-e5fed5a87abe", "45db04e4-304a-4933-9823-33f389e8d74d", "12cfb892-aac0-4c5b-94af-521852e46d6a",
                "cd3d059c-09f4-4ff3-8d63-bc765a5184fa", "ea660b10-85c4-4ae3-8a5f-41cea3648e3e", "0440483e-ca0e-4120-8c50-4c8cd9b965d6",
                "58611129-2dbc-4a81-a72f-77ddfc1b1b49", "45204234-adfd-45cb-a505-a8e7a676b114", "4e236f34-b981-41c3-8c65-f8c9000b94e7",
                "1b67aa9a-2e4a-45af-ac98-64d6ad15b16c", "758bf02e-3122-46e0-884e-67cf83df1786", "ebbb6b7c-945c-41ee-a792-de0e43191bd8",
                "dc2e6bd1-8156-4886-adff-b39e6043af0c", "112c1e67-726f-40b1-ac17-6974127bb9b9", "90b72513-afd4-4570-84de-a56c312fdf81",
                "d868e6ec-c44a-405b-8fa6-f7f0f8cfb500", "2de9426b-914a-4a06-a3a0-5e6d9d3886f6", "578ae244-7750-4d9f-867b-f3cd3d6fecf4",
                "67405111-37a5-438f-81cc-4666af60c800", "5fdfb320-2a02-49a7-94ff-5ca418cae602", "ff24da26-a969-4f0e-ba1e-a122ead6c6e3"
        };

        String[] movieTrailer = {
             "FWjiXOqRKhk", "9nzpk_Br6yo", "4vPeTSRd580", "iwROgK94zcM", "4bG17OYs-GA", "K53oROH2sx0", "92a7Hj0ijLs",
                "1C9ujuCPlnY", "5gSKk-wwLsY", "_7cowIHjCD4", "Zc4obWGVgGs", "5PuTm8Pz5CQ", "ByXuk9QqQkk", "8hxYx3Jq3kI",
                "Gp-H_YOcYTM", "FRFAujm3rik", "QfkrMq2G71g", "W71mtorCZDw", "jGr8XDxB-9I", "jjmrxqcQdYg", "0pVkiod6V0U"
        };

        Intent i = getIntent();
        int position = i.getExtras().getInt("id");

        filmTitle = findViewById(R.id.filmName);
        originalTitle = findViewById(R.id.originalTitle);
        romanisedTitle = findViewById(R.id.romanisedTitle);
        director = findViewById(R.id.director);
        producer = findViewById(R.id.producer);
        releaseDate = findViewById(R.id.releaseDate);
        runTime = findViewById(R.id.runTime);
        description = findViewById(R.id.description);
        trailer = findViewById(R.id.trailer);
        getLifecycle().addObserver(trailer);

        trailer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId;
                switch(position){
                    case 0: videoId = movieTrailer[position];
                        break;
                    case 1: videoId = movieTrailer[position];
                        break;
                    case 2: videoId = movieTrailer[position];
                        break;
                    case 3: videoId = movieTrailer[position];
                        break;
                    case 4: videoId = movieTrailer[position];
                        break;
                    case 5: videoId = movieTrailer[position];
                        break;
                    case 6: videoId = movieTrailer[position];
                        break;
                    case 7: videoId = movieTrailer[position];
                        break;
                    case 8: videoId = movieTrailer[position];
                        break;
                    case 9: videoId = movieTrailer[position];
                        break;
                    case 10: videoId = movieTrailer[position];
                        break;
                    case 11: videoId = movieTrailer[position];
                        break;
                    case 12: videoId = movieTrailer[position];
                        break;
                    case 13: videoId = movieTrailer[position];
                        break;
                    case 14: videoId = movieTrailer[position];
                        break;
                    case 15: videoId = movieTrailer[position];
                        break;
                    case 16: videoId = movieTrailer[position];
                        break;
                    case 17: videoId = movieTrailer[position];
                        break;
                    case 18: videoId = movieTrailer[position];
                        break;
                    case 19: videoId = movieTrailer[position];
                        break;
                    case 20: videoId = movieTrailer[position];
                    break;
                    case 21: videoId = movieTrailer[position];
                    break;
                    default: videoId = "z4WCaWJgOqM";
                }
                youTubePlayer.cueVideo(videoId,0);
            }
        });

        mQueue = Volley.newRequestQueue(this);

        url = url+movieId[position];
        jsonParse(url);
        Log.d("test_url", url);

    }

    private void jsonParse(String url){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    String film = response.getString("title");
                    filmTitle.setText(film);
                    String japaneseTitle = response.getString("original_title");
                    originalTitle.setText(japaneseTitle);
                    String romanised = response.getString("original_title_romanised");
                    romanisedTitle.setText(romanised);
                    String dir = response.getString("director");
                    director.setText(dir);
                    String prod = response.getString("producer");
                    producer.setText(prod);
                    String relDate = response.getString("release_date");
                    releaseDate.setText(relDate);
                    String runT = response.getString("running_time");
                    runTime.setText(runT);
                    String descrip = response.getString("description");
                    description.setText(descrip);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}