package com.maceghost.test.bikechooser;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class BikeChooserAdapter extends SimpleAdapter {

    //    colors were moved to MainActivity
    //    private int[] colors = new int[] { 0x00AD4747, 0x00475EAD };

    List<HashMap<String, String>> items;
    Context context = null;

    // set up the custom adapter here
    // TODO : refactor hashmap to model class (BikeInfo) if the row information was more complicated

    /**
     *
     * @param context context is needed to find resources/etc.
     * @param items list of items (hashmap) that will provide the records to be displayed
     * @param resource @see SimpleAdapter
     * @param from @see SimpleAdapter
     * @param to @see SimpleAdapter
     */
    public BikeChooserAdapter(Context context, List<HashMap<String, String>> items, int resource, String[] from, int[] to) {
        super(context, items, resource, from, to);
        this.items = items;
        this.context = context;
    }

    /**
     *
     * @param position row entry to be processed
     * @param convertView view to be converted
     * @param parent parent view that holds this list element
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // trick to alternate the row background color
        View rowView = super.getView(position, convertView, parent);
        int colorPos = position % MainActivity.colors.length;
        rowView.setBackgroundColor(MainActivity.colors[colorPos]);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
// TODO : images are white background which IMO doesn't work with list color coding
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setBackgroundColor(MainActivity.colors[colorPos]);

        String inputurl = items.get(position).get("image")+".jpg";

        try {
            InputStream is= context.getResources().getAssets().open(inputurl);
            Drawable bikeImage = Drawable.createFromStream(is, "src");

            imageView.setMaxHeight(100);
            imageView.setMaxWidth(100);
            imageView.setImageDrawable(bikeImage);

        } catch (Exception e) {
            Log.e(MainActivity.TAG, "Adapter.getView : "+e);
        }

        return rowView;
    }
}