package com.maceghost.test.bikechooser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener {

    View rootView = null;
    ArrayList<HashMap<String, String>> bikeListData = null;

    public MainFragment() {
        if (rootView != null) {
            Button button = (Button) this.getView().findViewById(R.id.loginButton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity mainActivity = (MainActivity) MainFragment.this.getActivity();
                    mainActivity.showNextFragment();
                }
            });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
   public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
//                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//                "Linux", "OS/2" };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, values);
//        setListAdapter(adapter);

        bikeListData = loadData("bike_products.json");
        // http://eureka.ykyuen.info/2010/01/03/android-simple-listview-using-simpleadapter/
//        ArrayList<HashMap<String, String>> bikeListData = new ArrayList<HashMap<String, String>>();

        String[] columnTags = new String[]{"brand", "model", "price", "image"};

        int[] columnIds = new int[]{R.id.brand, R.id.model, R.id.price, R.id.image};
//        for (int i = 0; i < 4; i++) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            //initialize row data
//            for (int j = 0; j < 3; j++) {
//                map.put(columnTags[j], "row"+i+"col" + j);
//            }
//            bikeListData.add(map);
//        }
        ListView list = (ListView) rootView.findViewById(R.id.bikeList);
        BikeChooserAdapter arrayAdapter = new BikeChooserAdapter(getActivity(), bikeListData, R.layout.bike_list_row, columnTags, columnIds);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(MainFragment.this);
//        ListView list = (ListView)rootView.findViewById(R.id.bikeList);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        HashMap<String, String> item = bikeListData.get(position);
        MainActivity activity = (MainActivity)MainFragment.this.getActivity();
        LayoutInflater li = LayoutInflater.from(activity);
        ViewGroup detailView = (ViewGroup)li.inflate(R.layout.fragment_detail, null);

        DetailFragment f = new DetailFragment();
        Bundle b = new Bundle();
        f.setArguments(b);
        f.setItem(item);
        f.setRootView(detailView);
        activity.showDetailFragment(detailView);
    }

    public ArrayList<HashMap<String, String>> loadData(String inFile) {
        ArrayList<HashMap<String, String>> results = null;

        try {
            InputStream stream = MainFragment.this.getActivity().getAssets().open(inFile);
            ObjectMapper mapper = new ObjectMapper();
            results = mapper.readValue(stream, ArrayList.class);
        } catch (IOException e) {
            // Handle exceptions here
        }
        return results;

    }


}
