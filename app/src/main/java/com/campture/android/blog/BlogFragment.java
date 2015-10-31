package com.campture.android.blog;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campture.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogFragment extends Fragment {

    public static BlogFragment newInstance() {
        Bundle args = new Bundle();

        BlogFragment fragment = new BlogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public BlogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }


}
