package com.campture.android.tours;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campture.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourDiscoverFragment extends Fragment {

    public static TourDiscoverFragment newInstance() {

        Bundle args = new Bundle();

        TourDiscoverFragment fragment = new TourDiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public TourDiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tour_discover, container, false);
    }


}
