package com.example.ghiblistudioinfo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class imageAdapter extends BaseAdapter {

    private Context mContext;

    public int[] imageArray = {
            R.drawable.castle_in_the_sky_poster, R.drawable.from_up_on_poppy_hill_movie_poster, R.drawable.grave_of_the_fireflies_poster,
            R.drawable.howls_moving_castle,R.drawable.kikis_delivery_service,R.drawable.mononoke_hime, R.drawable.my_neighbor_totoro,
            R.drawable.my_neighbors_the_yamadas, R.drawable.only_yesterday, R.drawable.pom_poko, R.drawable.ponyo, R.drawable.porco_rosso,
            R.drawable.spirited_away, R.drawable.tales_from_earth_sea, R.drawable.the_cat_returns, R.drawable.the_red_turtle,
            R.drawable.the_secret_world_of_arriety, R.drawable.the_tale_of_the_princess_kaguya, R.drawable.the_wind_rises, R.drawable.when_marnie_was_there,
            R.drawable.whisper_of_the_heart
    };

    public imageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(340, 350));

        return imageView;
    }
}
