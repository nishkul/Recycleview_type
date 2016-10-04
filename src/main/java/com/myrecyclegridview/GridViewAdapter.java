package com.myrecyclegridview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by rajesh on 1/4/16.
 */
public class GridViewAdapter extends BaseAdapter {

    //Imageloader to load images
    private ImageLoader imageLoader;
    private LayoutInflater inflater;
    private Activity activity;
    //Context
    private Context context;

    //Array List that would contain the urls and the titles for the images
    private ArrayList<String> images;
    private ArrayList<String> names;

    public GridViewAdapter (MainActivity activity, ArrayList<String> images, ArrayList<String> names){
        //Getting all the values
        this.activity = activity;
        this.images = images;
        this.names = names;
    }

    public GridViewAdapter(FullImageActivity fullImageActivity) {
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Creating a linear layout

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null)
                convertView = inflater.inflate(R.layout.single_layout, null);


//        LinearLayout linearLayout = new LinearLayout(context);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);


        //NetworkImageView
//        NetworkImageView networkImageView = new NetworkImageView(context);
        NetworkImageView networkImageView = (NetworkImageView)convertView.findViewById(R.id.image) ;
        TextView title = (TextView)convertView.findViewById(R.id.title);

        //Initializing ImageLoader
        imageLoader = CustomVolleyRequest.getInstance(activity).getImageLoader();
        imageLoader.get(images.get(position), ImageLoader.getImageListener(networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        //Setting the image url to load
        networkImageView.setImageUrl(images.get(position),imageLoader);

        //Creating a textview to show the title
//        TextView textView = new TextView(context);
        title.setText(names.get(position));

        //Scaling the imageview
//        networkImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        networkImageView.setLayoutParams(new GridView.LayoutParams(300,300));

        //Adding views to the layout
//        linearLayout.addView(textView);
//        linearLayout.addView(networkImageView);

        //Returnint the layout
        return convertView;
    }
}
