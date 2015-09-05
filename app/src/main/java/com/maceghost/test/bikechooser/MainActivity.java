package com.maceghost.test.bikechooser;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "BikeChooser";
    View rootView = null;
    ViewAnimator animator = null;
    public static String PACKAGE_NAME = null;
    public static int[] colors = new int[] { 0x30FF0000, 0x300000FF };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        PACKAGE_NAME = getApplicationContext().getPackageName();;
        setContentView(R.layout.activity_main);
        ViewGroup parent = (ViewGroup) findViewById(R.id.main_layout);
        rootView = (ViewGroup)findViewById(R.id.main_layout);
        animator = (ViewAnimator)rootView.findViewById(R.id.animator);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showFragment(int fid) {
        int fragmentId = 0;
        switch (fid) {
            case 0:
                fragmentId = R.layout.fragment_login;
                break;
            case 1:
                fragmentId = R.layout.fragment_main;
                break;
            case 2:
                fragmentId = R.layout.fragment_detail;
                break;
        }
        animator.setDisplayedChild(fragmentId);

    }

    public void showNextFragment() {
        animator.showNext();
    }

    public void showPreviousFragment() {
        animator.showPrevious();
    }

    public void showDetailFragment(View view) {
        animator.addView(view);
        animator.setDisplayedChild(2);
    }

    public void showDetailFragment(Map<String, String> item) {

        int fragmentId = R.layout.fragment_detail;
        animator.setDisplayedChild(fragmentId);

    }

    public void removeDetailFragment() {

        animator.showPrevious();
        animator.removeViewAt(2);
//        int fragmentId = R.layout.fragment_main;
//        animator.setDisplayedChild(fragmentId);

    }

}
