package com.myrecyclegridview;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rajesh on 1/4/16.
 */
public class RecycleViewActivity extends AppCompatActivity {

    public static final String DATA_URL = "http://api.androidhive.info/json/movies.json";

    private static RecyclerView gridRecyclerView;
    private static MyRecyclerViewAdapter adapter;
    public static final String TAG_IMAGE_URL = "image";
    public static final String TAG_NAME = "title";
    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> title;
    private static String navigateFrom = " ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_layout);

//        Log.d("navigateFrom==>",navigateFrom);

        gridRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        gridRecyclerView.setHasFixedSize(true);// set it fixed size


        navigateFrom = getIntent().getStringExtra("navigateFrom");//Get Intent Value in String
        Log.d("navigateFrom ===",navigateFrom);

        if (navigateFrom.equals("Horizontal"))
        {
//            getSupportActionBar().setTitle("Horizontal");
            gridRecyclerView.setLayoutManager(new LinearLayoutManager(RecycleViewActivity.this, LinearLayoutManager.HORIZONTAL, false));
        }
        else if (navigateFrom.equals("Staggered"))
        {
//            getSupportActionBar().setTitle("Staggered");
            gridRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));// Here 2 is no. of columns to be displayed

        }
        else
        {
            // Set layout manager, we need grid layout manager here for gridview
            gridRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        }
        images = new ArrayList<>();
        title = new ArrayList<>();

        //Calling the getData method
        getData();
    }



    private void getData(){
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();

                        //Displaying our grid
                        populatRecyclerView(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }


    private void populatRecyclerView(JSONArray jsonArray){
        //Looping through all the elements of json array
        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);

                //getting image url and title from json object
                images.add(obj.getString(TAG_IMAGE_URL));
                title.add(obj.getString(TAG_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Creating GridViewAdapter Object
        adapter = new MyRecyclerViewAdapter(this, images,title);

        // set adapter over recyclerview
        gridRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
