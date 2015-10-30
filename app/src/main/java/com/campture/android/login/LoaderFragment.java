package com.campture.android.login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campture.android.MainActivity;
import com.campture.android.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoaderFragment extends Fragment {

    public LoaderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after a delay
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        }, 2500);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_loader, container, false);
    }
}
