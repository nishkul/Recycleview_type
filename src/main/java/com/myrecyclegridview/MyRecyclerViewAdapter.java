package com.myrecyclegridview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

/**
 * Created by rajesh on 1/4/16.
 */
public class MyRecyclerViewAdapter  extends RecyclerView.Adapter<MyAdapterViewHolder>  {


    private ImageLoader imageLoader;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> images;
    private ArrayList<String> names;

    public MyRecyclerViewAdapter(Context context, ArrayList<String> images, ArrayList<String> names) {
//        this.itemList = itemList;
        this.context = context;
        this.images = images;
        this.names = names;
    }

    @Override
    public MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        MyAdapterViewHolder rcv = new MyAdapterViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(MyAdapterViewHolder holder, int position) {
        holder.countryName.setText(names.get(position));
        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        imageLoader.get(images.get(position), ImageLoader.getImageListener(holder.networkImageView, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.networkImageView.setImageUrl(images.get(position),imageLoader);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


//    private final String TAG = MyRecyclerViewAdapter.class.getSimpleName();
//
//    // create the view
//    @Override
//    public MyAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d(TAG, "onCreateViewHolder(ViewGroup, viewType: " + viewType + ")");
//
//        // inflate layout
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
//
//        // parse the view into view holder
//        return new MyAdapterViewHolder(itemView);
//    }
//
//    // bind the data with the view to show on UI
//    @Override
//    public void onBindViewHolder(MyAdapterViewHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder(MyAdapterViewHolder, position: " + position + ")");
//
//        // set the name base on the position
//        holder.tvCardName.setText("position: " + position);
//
//        holder.iPosition = position;
//
//        // JUST DEMO to randomize views for StaggeredGridLayoutManager
//        int remainder = position % MyAdapterViewHolder.cardPhotoResIds.length;
//
//        for(int i = 0; i < MyAdapterViewHolder.cardPhotoResIds.length; i++) {
//            if(i <= remainder) {
//                holder.ivCardPhotos[i].setVisibility(View.VISIBLE);
//            } else {
//                holder.ivCardPhotos[i].setVisibility(View.GONE);
//            }
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        Log.d(TAG, "getItemCount()");
//        return 100;
//    }
}
//MyAdapterViewHolder.java
class MyAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final String TAG = MyAdapterViewHolder.class.getSimpleName();

    public TextView countryName;

   public NetworkImageView networkImageView;

    public MyAdapterViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
         networkImageView = (NetworkImageView)itemView.findViewById(R.id.country_photo) ;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }

//    public TextView tvCardName;
//
//    public static final int cardPhotoResIds[] = new int[] { R.id.iv_card_photo1, R.id.iv_card_photo2, R.id.iv_card_photo3, R.id.iv_card_photo4 };
//    public ImageView ivCardPhotos[] = new ImageView[cardPhotoResIds.length];
//
//    public int iPosition;
//
//    public MyAdapterViewHolder(View itemView) {
//        super(itemView);
//
//        tvCardName = (TextView) itemView.findViewById(R.id.tv_card_name);
//
//        for(int i = 0; i < cardPhotoResIds.length; i++) {
//            ivCardPhotos[i] = (ImageView) itemView.findViewById(cardPhotoResIds[i]);
//        }
//
//        // register click listener
//        itemView.setOnClickListener(this);
//    }
//
//    /**
//     * process on click listener
//     * @param v
//     */
//    @Override
//    public void onClick(View v) {
//        Log.d(TAG, "onClick(view), position: " + iPosition);
//    }
}
