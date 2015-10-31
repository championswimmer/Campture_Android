package com.campture.android.login;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.campture.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener {

    AppCompatEditText inputEmail, inputPassword, inputName;
    AppCompatButton btnSignin, btnSignup;
    String name;

    public static SignupFragment newInstance() {

        Bundle args = new Bundle();

        SignupFragment fragment = new SignupFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);

        inputEmail = (AppCompatEditText) rootView.findViewById(R.id.edittext_email);
        inputPassword = (AppCompatEditText) rootView.findViewById(R.id.edittext_password);
        inputName = (AppCompatEditText) rootView.findViewById(R.id.edittext_name);

        btnSignin = (AppCompatButton) rootView.findViewById(R.id.button_signin_submit);
        btnSignup = (AppCompatButton) rootView.findViewById(R.id.button_signup_submit);

        btnSignin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_signup_submit:

                if (inputName.getVisibility() == View.GONE) {
                    inputName.setVisibility(View.VISIBLE);
                } else if (validateEmpty(inputEmail, inputName, inputPassword)) {

                    ((LoginActivity) getActivity()).handleEmailSignup(
                            inputEmail.getText().toString(),
                            inputPassword.getText().toString(),
                            name
                    );
                }
                break;
            case R.id.button_signin_submit:
                if (validateEmpty(inputEmail, inputName, inputPassword)) {
                    ((LoginActivity) getActivity()).handleEmailSignin(inputEmail.getText().toString(), inputPassword.getText().toString());

                }
                break;
        }
    }

    public boolean validateEmpty (AppCompatEditText... ets) {
        for (AppCompatEditText et : ets) {
            if (et.getText().toString().length() < 1) {
                Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}
