package com.myrecyclegridview.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.myrecyclegridview.R;

/**
 * Created by rajesh on 27/4/16.
 */

public class CustomAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    int[] mResources = {
            R.drawable.gallery,
            R.drawable.gallery,
            R.drawable.gallery
    };

    public CustomAdapter(Activity listactivity) {
        this.mContext = listactivity;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.single_view_layout,container,false);

        ImageView image = (ImageView)itemView.findViewById(R.id.imageView);
        image.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
