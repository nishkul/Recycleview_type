package com.myrecyclegridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by rajesh on 14/4/16.
 */
public class FullImageActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_layout);

        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        GridViewAdapter imageAdapter = new GridViewAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource((Integer) imageAdapter.getItem(position));

    }
}
