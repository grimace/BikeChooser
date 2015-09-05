package com.maceghost.test.bikechooser;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Displays the detail information for an individual bike record
 */
public class DetailFragment extends Fragment {

    ViewGroup rootView = null;
    Map<String, String> itemMap = new HashMap<String, String>();


    public DetailFragment() {}

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//
//
//    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//    }

    public void setItem(Map<String, String> itemMap) {
        this.itemMap = itemMap;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_detail, container, false);
//        Button loginButton = (Button)rootView.findViewById(R.id.addButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity mainActivity = (MainActivity) DetailFragment.this.getActivity();
//                mainActivity.showNextFragment();
//            }
//        });
//        rootView.setBackgroundColor(MainActivity.colors[0]);
//
//        return rootView;
//    }

//    public ViewGroup getRootView() {
//        return rootView;
//    }

    /**
     *
     * @param view Fragments view gets passed in
     */
    public void setRootView(ViewGroup view) {
        rootView = view;
        setViewDetail();
    }

    /**
     * update the view elements after the
     */
    private void setViewDetail() {

        final MainActivity activity = (MainActivity)rootView.getContext();
        rootView.setBackgroundColor(MainActivity.colors[0]);

        String brand = itemMap.get("brand");
        TextView brandView = (TextView) rootView.findViewById(R.id.detail_brand);
        brandView.setText(brand);

        String model = itemMap.get("model");
        TextView modelView = (TextView) rootView.findViewById(R.id.detail_model);
        modelView.setText(model);

        String price = itemMap.get("price");
        TextView priceView = (TextView) rootView.findViewById(R.id.detail_price);
        priceView.setText(price);

        String imageItem = itemMap.get("image");
        ImageView imageView = (ImageView) rootView.findViewById(R.id.detail_image);

        String inputurl = imageItem + ".jpg";

        try {
            InputStream is = activity.getResources().getAssets().open(inputurl);
            Drawable bikeImage = Drawable.createFromStream(is, "src");
// TODO : make image fit color scheme better, and appear squarely in field
//            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setMaxHeight(200);
            imageView.setMaxWidth(200);
            imageView.setImageDrawable(bikeImage);

        } catch (Exception e) {
            Log.e(MainActivity.TAG, "Adapter.getView : " + e);
        }
        // TODO : there should also be a cancel button,  add to cart should actually add to a cart
        final Button addCartButton = (Button) rootView.findViewById(R.id.addButton);
        addCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // disable the button so the user can't click x2
                addCartButton.setEnabled(false);
                // simple toast to indicate action
                Toast.makeText(activity, "Item has been added to cart", Toast.LENGTH_SHORT).show();
                // allow the screen to stay up for a short period so the user can be reminded what was added to cart

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        activity.
                         runOnUiThread(
                                 new Runnable() {
                                     public void run() {
                                         // then go back to list screen
                                         activity.removeDetailFragment();
                                     }
                                 });
                    }
                }, 1000);

            }

        });
    }
}
