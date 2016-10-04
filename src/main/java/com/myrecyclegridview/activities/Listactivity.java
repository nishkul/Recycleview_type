package com.myrecyclegridview.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.myrecyclegridview.R;
import com.myrecyclegridview.adapter.CustomAdapter;
import com.myrecyclegridview.common.CustomListView;
import com.myrecyclegridview.utils.Common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

//import com.androprogrammer.tutorials.samples.AppsListviewDemo;


public class Listactivity extends Baseactivity implements ListView.OnItemClickListener, SearchView.OnQueryTextListener, CustomListView.ScrollObserver
{
    protected View view;
    protected CustomListView lv_tutorials;
    protected File image;
    protected ImageButton btShare;
    protected ViewPager pager;
    protected ArrayAdapter<String> adapter = null;

    private static final String TAG = "Listactivity";
    private static final int REQUEST_CODE = 1234;

    private ComponentName mDeviceAdminSample;
    private SearchView sv;
    private MenuItem searchItem;

    public static int REQUEST_CODE_ENABLE_ADMIN = 1111;

    public static Listactivity listActivity;
    public Map<String,Object> tutorials;


    //private Logger rootLogger;

    //ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger)
           // LoggerFactory.getLogger(this.getClass().getSimpleName());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setReference();

//        setToolbarElevation(7);


        //rootLogger.setLevel(Level.ALL);

        //toolbar.setLogo(R.mipmap.ic_launcher);

        addListItem();

//        mainlayout.post(new Runnable() {
//            @Override
//            public void run() {
//                captureScreen();
//            }
//        });

        //Log.d("List", "" + tutorials.size());

        // Launch the activity to have the user enable our admin.
        /*Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                getResources().getString(R.string.admin_receiver_status_disable_warning));
        startActivityForResult(intent, REQUEST_CODE_ENABLE_ADMIN);*/


        String[] keys = tutorials.keySet().toArray(
                new String[tutorials.keySet().size()]);

        adapter = new ArrayAdapter<String>(this,R.layout.row_mainlist_item,R.id.row_tutorial_title,keys);

        lv_tutorials.setAdapter(adapter);

        lv_tutorials.setOnItemClickListener(this);
        lv_tutorials.setTextFilterEnabled(false);


//        btShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//                sharingIntent.setType("image/jpg");
//                sharingIntent.putExtra(Intent.EXTRA_TITLE, getApplicationInfo().name);
//                sharingIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(image));
//                startActivity(Intent.createChooser(sharingIntent, "Share App using"));
//                //startVoiceRecognitionActivity();
//
//            }
//        });

        // To access it from service.
        listActivity = this;

    }

    @Override
    public void setReference() {
        view = LayoutInflater.from(this).inflate(R.layout.activity_mainlist, container);
        lv_tutorials = (CustomListView) view.findViewById(R.id.lv_tutorials);
        lv_tutorials.setListener(this);

        lv_tutorials.addHeaderView(getLayoutInflater().inflate(R.layout.mainlist_header, null));
         pager = (ViewPager)lv_tutorials.findViewById(R.id.viewpager);
        CustomAdapter mpager = new CustomAdapter(this);

        pager.setAdapter(mpager);


//        btShare = (ImageButton) view.findViewById(R.id.list_bt_share);

        int color = Common.getThemeColor(this,R.attr.colorAccent);

//        btShare.getBackground().setColorFilter(color, PorterDuff.Mode.ADD);
        tutorials = new TreeMap<String,Object>();

        //rootLogger = LoggerFactory.getLogger(this.getClass().getSimpleName());

//        mDeviceAdminSample = new ComponentName(this, DeviceAdminReciver.class);

    }

    public static Listactivity getInstance()
    {
        return listActivity;
    }

    private void hideViews()
    {
        //Log.d(TAG, "" + rootLogger.isDebugEnabled());
        //rootLogger.debug("hideview");
//        toolbar.animate().alpha(0).setDuration(300)
//                .translationY(-toolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));

//        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) btShare.getLayoutParams();
//        int fabBottomMargin = lp.bottomMargin;

//        btShare.animate().setDuration(300).translationY(btShare.getHeight()+fabBottomMargin)
//                .setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        //rootLogger.debug("showview");
//        toolbar.animate().alpha(1).setDuration(300).translationY(0).setInterpolator(new DecelerateInterpolator(2));
//        btShare.animate().setDuration(300).translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    public void addListItem()
    {
        tutorials.put("Async File Read", null);
        tutorials.put("Battery Status",null);
        tutorials.put("Volume Setting", null);
        tutorials.put("Frame Animation", null);
        tutorials.put("Video Player", null);
        tutorials.put("Circular Image View", null);
        tutorials.put("Track User Location", null);
        tutorials.put("Crop and Resize Image", null);
        tutorials.put("Image Grid View", null);
        tutorials.put("Image Switcher", null);
        tutorials.put("Tabs with Toolbar", null);
        tutorials.put("Icon Tabs with Toolbar", null);
        tutorials.put("Push Notification", null);
        tutorials.put("View pager with circular indicator", null);
        tutorials.put("Voice input using Google", null);
        tutorials.put("Change Theme",null);
        tutorials.put("Tutorial Recycler View", null);
        tutorials.put("Fragment Animation", null);
        tutorials.put("Stack view ", null);
        tutorials.put("Data Caching ", null);
    }

    private void captureScreen(){

        // create bitmap screen capture
        mainlayout.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(mainlayout.getDrawingCache());
        mainlayout.setDrawingCacheEnabled(false);

        image = new File(Environment.getExternalStorageDirectory().toString(), "share.jpg");

        OutputStream fout = null;
        try {
            fout = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fout);
            fout.flush();
            fout.close();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_list, menu);
//
//        searchItem = menu.findItem(R.id.search);
//        sv = (SearchView) MenuItemCompat.getActionView(searchItem);
//        sv.setOnQueryTextListener(this);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        //int id = item.getItemId();
//
//        switch (item.getItemId()){
//
//            case R.id.action_settings :
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String key = (String) parent.getItemAtPosition(position);
        startActivity(new Intent((Intent) tutorials.get(key)));
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        if (TextUtils.isEmpty(s))
        {
            adapter.getFilter().filter("");
        }
        else
        {
            adapter.getFilter().filter(s);
        }

        return true;
    }

    @Override
    public void onHide() {
        hideViews();
    }

    @Override
    public void onShow() {
        showViews();
    }

    /**
     * Fire an intent to start the voice recognition activity.
     */
    private void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak the word");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If Voice recognition is successful then it returns RESULT_OK
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> textMatchList = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                if (!textMatchList.isEmpty()) {
                    String Query = textMatchList.get(0);
                    if (searchItem != null)
                    {
                        searchItem.expandActionView();
                        sv.setQuery(Query,true);
                    }
                    //word.setText(searchQuery);
                }
                //Result code for various error.
            } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
                Common.showToast(this,"Network Error");
            } else if (resultCode == RecognizerIntent.RESULT_NO_MATCH) {
                Common.showToast(this, "No Match");
            } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
                Common.showToast(this, "Server Error");
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
