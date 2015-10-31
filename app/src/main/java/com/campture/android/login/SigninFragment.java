package com.campture.android.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.campture.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment  implements View.OnClickListener{

    Button btnFbSignin, btnTwitterSignin;

    public static SigninFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SigninFragment fragment = new SigninFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_signin, container, false);
        btnFbSignin = (Button) rootView.findViewById(R.id.button_facebook_signin);
        btnTwitterSignin = (Button) rootView.findViewById(R.id.button_twitter_signin);

        btnFbSignin.setOnClickListener(this);
        btnTwitterSignin.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_facebook_signin:
                ((LoginActivity) getActivity()).handleFacebookLogin();
                break;
            case R.id.button_twitter_signin:
                //Handle Twitter login
                break;
        }
    }
}
