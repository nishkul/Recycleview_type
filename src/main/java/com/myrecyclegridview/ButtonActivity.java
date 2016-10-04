package com.myrecyclegridview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myrecyclegridview.activities.Listactivity;

/**
 * Created by rajesh on 3/4/16.
 */
public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {


    Button simple_gridview,recycle_gridview,staggered_gridview,horizontal_gridview,list_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout);

        simple_gridview = (Button)findViewById(R.id.simple_grid_view);
        recycle_gridview = (Button)findViewById(R.id.grid_recycle);
        staggered_gridview = (Button)findViewById(R.id.staggered_grid);
        horizontal_gridview = (Button)findViewById(R.id.horizantal_grid);
        list_view =(Button)findViewById(R.id.listview) ;

        simple_gridview.setOnClickListener(this);
        recycle_gridview.setOnClickListener(this);
        staggered_gridview.setOnClickListener(this);
        horizontal_gridview.setOnClickListener(this);
        list_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.simple_grid_view:
                Intent simple = new Intent(this,MainActivity.class);
                startActivity(simple);
                break;
            case R.id.grid_recycle:
                Intent recycle = new Intent(this,RecycleViewActivity.class);
                recycle.putExtra("navigateFrom","Recycle");
                startActivity(recycle);
                break;
            case R.id.staggered_grid:
//                Log.d("Staggered","in");
                Intent staggered = new Intent(this,RecycleViewActivity.class);
                staggered.putExtra("navigateFrom","Staggered");
                startActivity(staggered);
                break;
            case R.id.horizantal_grid:
//                Log.d("Horizontal","in");
                Intent horizontal = new Intent(this,RecycleViewActivity.class);
                horizontal.putExtra("navigateFrom","Horizontal");
                startActivity(horizontal);
                break;
            case R.id.listview:
                Intent list = new Intent(this, Listactivity.class);
                startActivity(list);
                break;
        }

    }
}
