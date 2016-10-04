package com.myrecyclegridview;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ScrollView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    //Web api url
    public static final String DATA_URL = "http://api.androidhive.info/json/movies.json";

    //Tag values to read from json
    public static final String TAG_IMAGE_URL = "image";
    public static final String TAG_NAME = "title";

    //GridView Object
    private GridView gridView;
    private ScrollView scrollview;

    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> title;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
         scrollview = (ScrollView)findViewById(R.id.scrollview);
        images = new ArrayList<>();
        title = new ArrayList<>();

//        scrollview.requestDisallowInterceptTouchEvent(false);
//        scrollview.smoothScrollTo(0, 0);
//        Calling the getData method
        getData();
//        gridView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
////                int action = event.getActionMasked();
////
////                switch (action) {
////                    case MotionEvent.ACTION_UP:
////                        scrollview.requestDisallowInterceptTouchEvent(false);
////                        break;
////                }
////                if(event.getAction() == MotionEvent.ACTION_MOVE){
////                    scrollview.requestDisallowInterceptTouchEvent(true);
////                    return true;
////                }
//                return false;
//            }
//        });


    }


    private void getData(){
        //Showing a progress dialog while our app fetches the data from url
        loading = new ProgressDialog(this, R.style.MyTheme);
        loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        loading.show(this, "Please wait...", "Fetching data...",false,false);

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();

                        //Displaying our grid
                        showGrid(response);
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


    private void showGrid(JSONArray jsonArray){
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
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,images,title);
//        MyRecyclerViewAdapter recyclerViewAdapter = new MyRecyclerViewAdapter(this,images,title);

        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(MainActivity.this, FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }


}
