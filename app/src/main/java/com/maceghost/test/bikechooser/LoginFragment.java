package com.maceghost.test.bikechooser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment {


    ViewGroup rootView = null;

    public LoginFragment() {
//        if (rootView != null) {
//            Button button = (Button) this.getView().findViewById(R.id.loginButton);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    MainActivity mainActivity = (MainActivity) LoginFragment.this.getActivity();
//                    mainActivity.showNextFragment();
//                }
//            });
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_login, container, false);
        Button loginButton = (Button)rootView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) LoginFragment.this.getActivity();
                mainActivity.showNextFragment();
            }
        });
        rootView.setBackgroundColor(MainActivity.colors[0]);
        return rootView;
    }
}
